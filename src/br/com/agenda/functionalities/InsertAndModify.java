package br.com.agenda.functionalities;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.details.contact.Contact;
import br.com.agenda.details.telephone.Telephone;

import java.util.Scanner;

public class InsertAndModify {
    public void addContact(Scanner scanner, ContactList contactList) {
        Contact newContact = new Contact();

        newContact.setId(contactList);

        scanner.nextLine(); //clear buffer
        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        addNumberTelephone(scanner, newContact);
        contactList.addDataBase(newContact);
    }

    public void addNumberTelephone(Scanner scanner, Contact contact) {
        Telephone newTelephone = new Telephone();

        System.out.print("Digite o DDD: ");
        newTelephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        newTelephone.setNumber(scanner.nextLong());

        contact.addTelephones(newTelephone);
    }

    public void rmContact(ContactList contactList, Scanner scanner) {
        contactList.displayList();

        if (contactList.getSizeList() > 0) {
            boolean removed;
            System.out.print("Informe o ID do contato que deseja remover: ");
            removed = contactList.rmDataBase(inputId(scanner));

            while (!removed) {
                System.out.print("Digite um ID válido: ");
                removed = contactList.rmDataBase(inputId(scanner));
            }
        }
    }

    public Long inputId(Scanner scanner) {
        try {
            Long id = scanner.nextLong();
            return id;
        } catch (Exception e) {
            scanner.nextLine();
            System.out.println("\u001B[31m" + "Erro: Você deve digitar um número inteiro." + "\u001B[0m"); //red
            System.out.print("Informe o ID do contato que deseja remover: ");
            return inputId(scanner);
        }
    }
}
