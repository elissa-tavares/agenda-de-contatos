package br.com.agenda.controller;

import java.util.Scanner;

import br.com.agenda.model.Contact;
import br.com.agenda.model.Telephone;
import br.com.agenda.service.ContactService;
import br.com.agenda.service.TelephoneService;

public class ContactController {

    static Scanner scanner;
    static ContactService contactService;
    static TelephoneService telephoneService;

    public ContactController() {
        scanner = new Scanner(System.in);
        contactService = new ContactService();
        telephoneService = new TelephoneService();
    }

    public int menu() {
        System.out.println("   >>>> Menu <<<<    ");
        System.out.println(" 1 - Listar Contatos  ");
        System.out.println(" 2 - Adicionar Contato");
        System.out.println(" 3 - Remover Contato  ");
        System.out.println(" 4 - Editar Contato   ");
        System.out.println(Color.RED + " 5 - Sair" + Color.RESET);
        System.out.print("Digite uma opção: ");

        int option = inputOption();
        if (contactService.listNotFound(option)) {
            System.out.println(Color.YELLOW + "Agenda de contatos vazia" + Color.RESET);
            return menu();
        }
        return option;
    }

    public int editMenu(Long idContact) {
        System.out.println("   >>>> Menu <<<<    ");
        System.out.println(" 1 - Inserir Telefone  ");
        System.out.println(" 2 - Remover Telefone");
        System.out.println(" 3 - Editar Telefone  ");
        System.out.println(" 4 - Editar Nome   ");
        System.out.println(Color.RED + " 5 - Sair" + Color.RESET);
        System.out.print("Digite uma opção: ");

        int option = inputOption();
        if (contactService.phoneBookNotFound(option, idContact)) {
            System.out.println(Color.YELLOW + "Lista telefônica vazia" + Color.RESET);
            return editMenu(idContact);
        }
        return option;
    }

    public void read() {
        System.out.println(contactService.displayList());
    }

    public void create() {
        Contact contact = new Contact();

        System.out.print("Nome: ");
        contact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        contact.setSurname(scanner.nextLine());
        contactService.create(contact, inputTelephone());
    }

    public void delete() {
        System.out.println(contactService.displayList());
        System.out.print("Digite o ID do contato que deseja remover: ");
        Long id = inputIdContact();
        contactService.delete(id);
    }

    public void update() {
        int option;
        Long id;

        do {
            System.out.println(contactService.displayList());
            System.out.println("Digite o ID do" + Color.YELLOW + " contato " + Color.RESET + "que deseja editar.");
            id = inputIdContact();

            option = editMenu(id);

            switch (option) {
                case 1 -> contactService.createTelephone(id, inputTelephone());
                case 2 -> contactService.deleteTelephone(id, inputIdTelephone(id));
                //case 3 -> contactService.updateNumber(inputIdTelephone(id), id);
                //case 4 -> contactService.updateName(inputName(), id);
            }
        } while (option != 5);
    }

    public void terminate() {
        //contactService.terminate();
        scanner.close();
    }

    public int inputOption() {
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5) {
                return option;
            } else {
                System.out.println(Color.RED + "Opção inválida" + Color.RESET);
                return inputOption();
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println(Color.RED + "Erro: Você deve digitar um número inteiro." + Color.RESET);
            return inputOption();
        }
    }

    public Telephone inputTelephone() {
        boolean validNumber;
        System.out.print("Digite o DDD: ");
        String ddd = scanner.nextLine();

        System.out.print("Digite o numero: ");
        Long number = scanner.nextLong();

        validNumber = telephoneService.validNumber(ddd, number);

        if (!validNumber) {
            System.out.println(Color.RED + "Telefone já existente." + Color.RESET);
            scanner.nextLine();
            return inputTelephone();
        }
        return new Telephone(ddd, number);
    }

    public Long inputIdContact() {
        boolean validId;
        Long id = inputId();
        validId = contactService.validateContactId(id);

        if (!validId) {
            System.out.print(Color.RED + "ID inexistente. Digite um ID valido: " + Color.RESET);
            scanner.nextLine();
            return inputIdContact();
        }
        return id;
    }

    public Long inputIdTelephone(Long idContact) {
        System.out.println(contactService.phoneDisplayList(idContact));
        System.out.print("Digite o ID do" + Color.YELLOW + " telefone " + Color.RESET + "que deseja editar: ");

        boolean validId;
        Long id = inputId();
        validId = telephoneService.validatePhoneId(id, idContact);

        if (!validId) {
            System.out.print(Color.RED + "ID inexistente. Digite um ID valido: " + Color.RESET);
            scanner.nextLine();
            return inputIdContact();
        }
        return id;
    }

    public Long inputId() {
        try {
            Long id = scanner.nextLong();
            scanner.nextLong();
            return id;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.print(Color.RED + "O ID deve ser um número. " + Color.RESET + "Digite novamente: ");
            return inputId();
        }
    }

//    public String[] inputName() {
//        String[] fullName = new String[2];
//
//        System.out.print("Nome: ");
//        fullName[0] = scanner.nextLine();
//
//        System.out.print("Sobrenome: ");
//        fullName[1] = scanner.nextLine();
//
//        return fullName;
//    }
}
