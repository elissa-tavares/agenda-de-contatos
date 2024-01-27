package br.com.agenda.data.base;

import br.com.agenda.details.contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {
    private List<Contact> contactList = new ArrayList<>();

    public Long getSizeList() {
        Long size = (long) contactList.size();
        return size;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void addDataBase(Contact newContact) {
        contactList.add(newContact);
        System.out.println("\u001B[32m" + "Contato adicionado com sucesso\n"+ "\u001B[0m"); //green
    }

    public boolean rmDataBase(Long id) {
        for (Contact contact : contactList){
            if(id == contact.getId()){ 
                contactList.remove(contact); //if removed
                System.out.println("\u001B[32m" + "Contato removido com sucesso\n"+ "\u001B[0m"); //green
                return true; //true
            } else {
                System.out.print("\u001B[31m" + "ID inexistente. " + "\u001B[0m"); //red
            }
        }
        return false;
    }
    
    public Long lastId() {
        Long size = getSizeList();
        Long lastId;
        if (size > 0) {
            lastId = contactList.get(Math.toIntExact(size-1L)).getId();
            return lastId;
        }
        return -1L; //there are no elements in the list
    }

    public void displayList() {
        int sizeList = contactList.size();

        if (sizeList > 0) {
            System.out.print("\u001B[34m"); //blue
            System.out.println("----------- AGENDA ----------");
            for (Contact contact : contactList) {
                System.out.print("\u001B[34m"); //blue
                System.out.println(contact.getId() + " - " + contact.getName() + " " + contact.getSurname());
                System.out.print(contact.formatPhoneList());
            }
            System.out.print("\u001B[34m"); //blue
            System.out.println("-----------------------------");
            System.out.print("\u001B[0m"); //reset
        } else {
            System.out.println("\u001B[33m" + "Agenda de contatos vazia" + "\u001B[0m\n");
        }
    }
}
