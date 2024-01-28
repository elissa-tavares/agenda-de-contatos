package br.com.agenda.functionalities;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.data.menu.Menu;
import br.com.agenda.details.Contact;
import br.com.agenda.details.Telephone;

import java.util.Scanner;

public class InsertAndModify {
    public Contact inputContact(Scanner scanner, ContactList contactList) {
        Contact newContact = new Contact();
        newContact.setId(contactList);
        newContact = inputFullName(scanner, newContact);

        newContact.addTelephones(inputNewTelefone(scanner, contactList, null));
        return newContact;
    }

    public Contact inputFullName(Scanner scanner, Contact newContact) {
        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        return newContact;
    }

    public Telephone inputNewTelefone(Scanner scanner, ContactList contactList, Long id) {
        Telephone newTelephone = new Telephone();
        newTelephone = inputNumber(scanner, newTelephone, contactList);
        newTelephone.setId(contactList, id);
        return newTelephone;
    }

    public Telephone inputNumber(Scanner scanner, Telephone newTelephone, ContactList contactList) {
        boolean validNumber;
        System.out.print("Digite o DDD: ");
        newTelephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        newTelephone.setNumber(scanner.nextLong());
        validNumber = contactList.validNumber(newTelephone.getDdd(), newTelephone.getNumber());

        if (!validNumber) {
            return inputNumber(scanner, newTelephone, contactList);
        }
        return newTelephone;
    }

    public void displayEditMenu() {
        String menu = """
                \u001B[33m    >>>> Edição <<<<   \u001B[0m
                 1 - Inserir Telefone  
                 2 - Remover Telefone  
                 3 - Editar Telefone   
                 4 - Editar Nome       
                 5 - \u001B[33mVoltar para o menu anterior\u001B[0m        
                """;
        System.out.println(menu);
        System.out.print("Digite uma opção: ");
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

    public boolean checkOptionsEditMenu(long option, Scanner scanner, ContactList contactList) {
        if (option == 5) {
            return false;
        }

        System.out.print("Digite o ID do" + "[33m contato [0m" + "que deseja editar: "); //yellow
        Long idContact = inputIdContact(scanner, contactList);

        switch ((int) option) {
            case 1: //bug aqui
                //System.out.println("entrou no case");
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
                Telephone telephone = new Telephone();
                if (checkContactList(contactList, idContact)) {
                    contactList.phoneDisplayList(idContact);
                    System.out.print("Digite o ID do" + "[33m telefone [0m" + "que deseja editar: "); //yellow
                    contactList.editTelephoneContact(inputIdTelephone(scanner, contactList, idContact), inputNumber(scanner, telephone, contactList), idContact);
                }
                break;
            case 4:
                Contact contact = new Contact();
                contactList.editNameContact(inputFullName(scanner, contact), idContact);
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