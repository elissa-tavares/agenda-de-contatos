package br.com.agenda.data.base;

import br.com.agenda.details.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private List<Contact> contactList = new ArrayList<>();

    public void addDataBase(Contact newContact) {
        this.contactList.add(newContact);
    }

    public  void displayList() {
        int sizeList = contactList.size();

        if (sizeList > 0) {
            System.out.println("\u001B[34m"); //blue
            System.out.println("----------- AGENDA ----------");
            for(Contact contact : contactList){
                System.out.println(contact.getId() + " - " + contact.getName() + " " + contact.getSurname());
                System.out.println(contact.formatPhoneList());
            }
            System.out.println("-----------------------------");
            System.out.println("\u001B[0m"); //reset
        } else {
            System.out.println("\u001B[33m" + "Agenda de contatos vazia" +  "\u001B[0m\n" );
        }
    }
}
