package br.com.agenda.functionalities;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.data.menu.Menu;
import br.com.agenda.details.Contact;
import br.com.agenda.details.Telephone;

import java.util.Scanner;

public class InsertAndModify {
    public Contact inputContact(Scanner scanner, ContactList contactList) {
        Contact newContact = inputFullName(scanner);
        newContact.setId(contactList);

        newContact.addTelephones(inputNewTelefone(scanner, contactList, null));
        return newContact;
    }

    public Contact inputFullName(Scanner scanner) {
        Contact contact = new Contact();
        System.out.print("Nome: ");
        contact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        contact.setSurname(scanner.nextLine());

        return contact;
    }

    public Telephone inputNewTelefone(Scanner scanner, ContactList contactList, Long id) {
        Telephone newTelephone = inputNumber(scanner, contactList);
        newTelephone.setId(contactList, id);
        return newTelephone;
    }

    public Telephone inputNumber(Scanner scanner, ContactList contactList) {
        Telephone telephone = new Telephone();
        boolean validNumber;
        System.out.print("Digite o DDD: ");
        telephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        telephone.setNumber(scanner.nextLong());
        validNumber = contactList.checkRepeatedNumbers(telephone.getDdd(), telephone.getNumber());

        if (!validNumber) {
            scanner.nextLine();
            return inputNumber(scanner, contactList);
        }
        return telephone;
    }

    public void displayEditMenu() {
        String menu = """
                \u001B[33m    >>>> Edição <<<<   \u001B[0m
                 1 - Inserir Telefone \s
                 2 - Remover Telefone \s
                 3 - Editar Telefone  \s
                 4 - Editar Nome      \s
                 5 - \u001B[33mVoltar para o menu anterior\u001B[0m       \s
                """;
        System.out.println(menu);
        System.out.print("Digite uma opção: ");
    }

    public void editContact(Scanner scanner, ContactList contactList) {
        Menu menu = new Menu();

        boolean performs = true, valid;
        int option;

        while (performs) {
            contactList.displayList();
            displayEditMenu();
            try {
                option = scanner.nextInt();
                scanner.nextLine();
                valid = menu.validateEntry(option);
                if (valid) {
                    performs = checkOptionsEditMenu(option, scanner, contactList);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\u001B[31m" + "Erro: Você deve digitar um número inteiro." + "\u001B[0m");
            }
        }
    }

    public boolean checkOptionsEditMenu(int option, Scanner scanner, ContactList contactList) {
        if (option == 5) {
            return false;
        }

        System.out.print("Digite o ID do" + "[33m contato [0m" + "que deseja editar: "); //yellow
        Long idContact = inputIdContact(scanner, contactList);

        switch (option) {
            case 1:
                contactList.addTelephoneContact(inputNewTelefone(scanner, contactList, idContact), idContact);
                break;
            case 2:
                if (checkContactList(contactList, idContact)) {
                    contactList.phoneDisplayList(idContact);
                    System.out.print("Digite o ID do" + "[33m telefone [0m" + "que deseja remover: "); //yellow
                    contactList.rmTelephoneContact(inputIdTelephone(scanner, contactList, idContact), idContact);
                }
                break;
            case 3:
                if (checkContactList(contactList, idContact)) {
                    contactList.phoneDisplayList(idContact);
                    System.out.print("Digite o ID do" + "[33m telefone [0m" + "que deseja editar: "); //yellow
                    contactList.editTelephoneContact(inputIdTelephone(scanner, contactList, idContact), inputNumber(scanner, contactList), idContact);
                }
                break;
            case 4:
                contactList.editNameContact(inputFullName(scanner), idContact);
                break;
        }
        return true;
    }

    public boolean checkContactList(ContactList contactList, Long idContato) {
        if (contactList.emptyTelephoneList(idContato)) {
            System.out.println("\u001B[31m" + "Não há nenhum telefone registrado nesse contato" + "\u001B[0m");
            return false;
        }
        return true;
    }

    public Long inputIdContact(Scanner scanner, ContactList contactList) {
        boolean validatedId;
        try {
            Long id = scanner.nextLong();
            scanner.nextLine();
            validatedId = contactList.verificationIdContact(id);
            if (validatedId) {
                return id;
            } else {
                System.out.print("\u001B[31m" + "ID inexistente. " + "\u001B[0m"); //red
                System.out.print("Digite um ID válido: ");
                return inputIdContact(scanner, contactList);
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.print("\u001B[31m" + "Erro: Você deve digitar um número inteiro. " + "\u001B[0m"); //red
            System.out.print("Digite um ID válido: ");
            return inputIdContact(scanner, contactList);
        }
    }

    public Long inputIdTelephone(Scanner scanner, ContactList contactList, long idContact) {
        boolean validatedId;
        try {
            Long id = scanner.nextLong();
            scanner.nextLine();
            validatedId = contactList.verificationIdTelephone(id, idContact);
            if (validatedId) {
                return id;
            } else {
                System.out.print("\u001B[31m" + "ID inexistente. " + "\u001B[0m"); //red
                System.out.print("Digite um ID válido: ");
                return inputIdTelephone(scanner, contactList, idContact);
            }
        } catch (Exception e) {
            scanner.nextLine();
            System.out.print("\u001B[31m" + "Erro: Você deve digitar um número inteiro. " + "\u001B[0m"); //red
            System.out.print("Digite um ID válido: ");
            return inputIdTelephone(scanner, contactList, idContact);
        }
    }
}