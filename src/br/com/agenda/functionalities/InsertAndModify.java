package br.com.agenda.functionalities;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.details.contact.Contact;
import br.com.agenda.details.telephone.Telephone;

import java.util.Scanner;

public class InsertAndModify {
    public void addContact(Scanner scanner, ContactList contactList, Long numId) {
        Contact newContact = new Contact();

        newContact.setId(numId);

        scanner.nextLine(); //clear buffer
        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        addNumberTelephone(scanner, newContact);
        contactList.addDataBase(newContact);
    }

    public void addNumberTelephone(Scanner scanner, Contact newContact){
        Telephone newTelephone = new Telephone();

        System.out.print("Digite o DDD: ");
        newTelephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        newTelephone.setNumber(scanner.nextLong());

        newContact.addTelephones(newTelephone);
    }
}
