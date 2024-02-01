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
        System.out.println("\n  >>>> Menu <<<<");
        System.out.println("1 - Listar Contatos");
        System.out.println("2 - Adicionar Contato");
        System.out.println("3 - Remover Contato");
        System.out.println("4 - Editar Contato");
        System.out.println(Color.RED + "5 - Sair" + Color.RESET);
        System.out.print("Digite uma opção: ");
        int option = inputOption();

        if (contactService.listNotFound(option)) {
            System.out.println(Color.YELLOW + "br.com.agenda.app.App de contatos vazia" + Color.RESET);
            return menu();
        }
        return option;
    }

    public int editMenu() {
        System.out.println("\n  >>>>" + Color.YELLOW + " Edição " + Color.RESET + "<<<<");
        System.out.println("1 - Inserir Telefone");
        System.out.println("2 - Remover Telefone");
        System.out.println("3 - Editar Telefone");
        System.out.println("4 - Editar Nome");
        System.out.print("Digite uma opção: ");

        return inputOption();
    }

    public void create() {
        System.out.println(Color.YELLOW + "\n-> Adicionar contato" + Color.RESET);
        contactService.create(inputName(), inputPhone());
        System.out.println(Color.GREEN + "Contato adicionado com sucesso" + Color.RESET);
    }

    public void read() {
        System.out.println(contactService.displayList());
    }

    public void update() { //melhorar isso
        System.out.println(Color.YELLOW + "\n-> Editar contato" + Color.RESET);
        int option;
        Long contactId;

        System.out.print(contactService.displayList());
        System.out.print("Digite o ID do" + Color.YELLOW + " contato " + Color.RESET + "que deseja editar: ");
        contactId = inputContactId();
        scanner.nextLine();

        option = editMenu();

        if (contactService.phoneBookNotFound(option, contactId)) {
            System.out.println(Color.YELLOW + "Lista telefônica vazia" + Color.RESET);
        }

        switch (option) {
            case 1 -> createPhone(contactId);
            case 2 -> deletePhone(contactId);
            case 3 -> updateNumber(contactId);
            case 4 -> updateName(contactId);
            default -> error();
        }
    }

    public void delete() {
        System.out.println(Color.YELLOW + "-> Remover contato" + Color.RESET);

        System.out.println(contactService.displayList());
        System.out.print("Digite o ID do contato que deseja remover: ");
        Long id = inputContactId();

        contactService.delete(id);
        System.out.println(Color.GREEN + "Contato removido com sucesso" + Color.RESET);
    }

    public void terminate() {
        //contactService.terminate();
        scanner.close();
    }

    public void error() {
        System.out.println(Color.RED + "Opção inválida" + Color.RESET);
    }

    public void createPhone(Long contactId){
        contactService.createPhone(contactId, inputPhone());
        System.out.println(Color.GREEN + "Telephone adicionado com sucesso" + Color.RESET);
    }

    public void updateNumber(Long contactId){
        Long phoneId = inputPhoneId(contactId); //guarda como long
        scanner.nextLine(); //consumes the line because it receives a ddd as nextLine
        contactService.updateNumber(contactId, phoneId, inputPhone());
        System.out.println(Color.GREEN + "Telefone atualizado com sucesso" + Color.RESET);
    }

    public void updateName(Long contactId){
        contactService.updateName(contactId, inputName());
        System.out.println(Color.GREEN + "Nome atualizado com sucesso" + Color.RESET);
    }

    public void deletePhone(Long contactId){
        contactService.deletePhone(contactId, inputPhoneId(contactId));
        System.out.println(Color.GREEN + "Telefone removido com sucesso" + Color.RESET);
    }

    public int inputOption() {
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            return option;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println(Color.RED + "Erro: Você deve digitar um número inteiro." + Color.RESET);
            return menu();
        }
    }

    public Contact inputName(){
        Contact contact = new Contact();

        System.out.print("Nome: ");
        contact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        contact.setSurname(scanner.nextLine());

        return contact;
    }

    public Telephone inputPhone() {
        boolean numberFound;
        System.out.print("Digite o DDD: ");
        String ddd = telephoneService.formatsDDD(scanner.nextLine());

        if (telephoneService.invalidDDD(ddd)){
            System.out.println(Color.RED + "DDD inválido. " + Color.RESET + "Tente novamente.");
            return inputPhone();
        }

        try {
            System.out.print("Digite o numero: ");
            Long number = scanner.nextLong();

            numberFound = telephoneService.validNumber(ddd, number);
            if (numberFound) {
                System.out.println(Color.RED + "Telefone já existente." + Color.RESET);
                scanner.nextLine();
                return inputPhone();
            }
            return new Telephone(ddd, number);

        } catch (Exception e){
            scanner.nextLine();
            System.out.println(Color.RED + "Você deve digitar um número inteiro. " + Color.RESET + "Tente novamente.");
            return inputPhone();
        }
    }

    public Long inputContactId() {
        boolean idFound;
        Long id = inputId();
        idFound = contactService.validateContactId(id);

        if (!idFound) {
            System.out.print(Color.RED + "ID inexistente. Digite um ID valido: " + Color.RESET);
            scanner.nextLine();
            return inputContactId();
        }
        return id;
    }

    public Long inputPhoneId(Long contactId) {
        System.out.println(Color.YELLOW + "-> Lista telefônica do contato " + contactId + Color.RESET);
        System.out.println(contactService.phoneDisplayList(contactId));
        System.out.print("Digite o ID do" + Color.YELLOW + " telefone: " + Color.RESET);

        boolean idFound;
        Long id = inputId();
        idFound = telephoneService.validatePhoneId(id, contactId);

        if (!idFound) {
            System.out.print(Color.RED + "ID inexistente. Digite um ID valido: " + Color.RESET);
            scanner.nextLine();
            return inputContactId();
        }
        return id;
    }

    public Long inputId() {
        try {
            return scanner.nextLong();
        } catch (Exception e) {
            scanner.nextLine();
            System.out.print(Color.RED + "O ID deve ser um número. " + Color.RESET + "Digite novamente: ");
            return inputId();
        }
    }
}
