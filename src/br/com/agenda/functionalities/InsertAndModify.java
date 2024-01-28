package br.com.agenda.functionalities;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.data.menu.Menu;
import br.com.agenda.details.contact.Contact;
import br.com.agenda.details.telephone.Telephone;

import java.util.Scanner;

public class InsertAndModify {
    public Contact inputContact(Scanner scanner, ContactList contactList) {
        Contact newContact = new Contact();
        Telephone newTelephone = new Telephone();

        newContact.setId(contactList);
        newContact = inputFullName(scanner, newContact);

        newTelephone.setId(null, null);
        newTelephone = inputNumber(scanner, newTelephone);

        newContact.addTelephones(newTelephone);
        return newContact;
    }

    public Contact inputFullName(Scanner scanner, Contact newContact) {
        scanner.nextLine();
        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        return newContact;
    }

    public Telephone inputNumber(Scanner scanner, Telephone newTelephone) {

        System.out.print("Digite o DDD: ");
        newTelephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        newTelephone.setNumber(scanner.nextLong());

        return newTelephone;
    }

    public void editContact(Scanner scanner, ContactList contactList) {
        Menu menu = new Menu();

        boolean performs = true, valid;
        Long option;

        while (performs) {
            contactList.displayList();
            displayEditMenu();
            try {
                option = scanner.nextLong();
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

    public void displayEditMenu() {
        String menu = """
                \u001B[33m    >>>> Edição <<<<   \u001B[0m
                 1 - Inserir Telefone  
                 3 - Remover Telefone  
                 3 - Editar Telefone   
                 4 - Editar Nome       
                 5 - Sair              
                """;
        System.out.println(menu);
        System.out.print("Digite uma opção: ");
    }

    public boolean checkOptionsEditMenu(long option, Scanner scanner, ContactList contactList) {
        if (option == 5) {
            return false;
        }

        System.out.print("Digite o ID do contato que deseja editar: ");
        Long idContact = inputIdContact(scanner, contactList);

        switch ((int) option) {
            case 1:
                contactList.addTelephoneContact(inputNewTelefone(scanner, contactList, idContact), idContact);
                break;
            case 2:
                contactList.phoneDisplayList(idContact);
                System.out.print("Digite o ID do telefone que deseja remover: ");
                contactList.rmTelephoneContact(inputIdTelephone(scanner, contactList, idContact), idContact);
                break;
            case 3:
                Telephone telephone = new Telephone();
                System.out.print("Digite o ID do telefone que deseja editar: ");
                contactList.editTelephoneContact(inputIdTelephone(scanner, contactList, idContact), inputNumber(scanner, telephone), idContact);
                break;
            case 4:
                Contact contact = new Contact();
                contactList.editNameContact(inputFullName(scanner, contact), idContact);
                break;
        }
        return true;
    }

    public Telephone inputNewTelefone(Scanner scanner, ContactList contactList, Long id) {
        Telephone newTelephone = new Telephone();
        newTelephone.setId(contactList, id);
        newTelephone = inputNumber(scanner, newTelephone);
        return newTelephone;
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