package br.com.agenda.data.base;

import br.com.agenda.details.contact.Contact;
import br.com.agenda.details.telephone.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactList {
    private List<Contact> contactList = new ArrayList<>();

    public Long getSizeList() {
        Long size = (long) contactList.size();
        return size;
    }

    public void addDataBase(Contact newContact) {
        contactList.add(newContact);
        System.out.println("\u001B[32m" + "Contato adicionado com sucesso\n" + "\u001B[0m"); //green
    }

    public void rmDataBase(Long id) {
        for (Contact contact : contactList) {
            if (Objects.equals(id, contact.getId())) {
                contactList.remove(contact);
                System.out.println("\u001B[32m" + "Contato removido com sucesso\n" + "\u001B[0m"); //green
            }
        }
    }

    public void addTelephoneContact(Telephone telephone, Long id) {
        for (Contact contact : contactList) {
            if (Objects.equals(id, contact.getId())) {
                contact.addTelephones(telephone);
                System.out.println("\u001B[32m" + "Telefone adicionado com sucesso\n" + "\u001B[0m"); //green
                break;
            }
        }
    }

    public boolean verificationId(Long id) {
        for (Contact contact : contactList) {
            if (Objects.equals(id, contact.getId())) {
                return true;
            }
        }
        return false;
    }

    public Long lastId() {
        return contactList.getLast().getId() + 1L;
        //return contactList.get(Math.toIntExact(contactList.size() - 1L)).getId();
    }

    public void displayList() {
        System.out.println("\u001B[34m" + "----------- AGENDA ----------" + "\u001B[0m");
        for (Contact contact : contactList) {
            System.out.print("\u001B[34m"); //blue
            System.out.println("\u001B[34m" + contact.getId() + " - " + contact.getName() + " " + contact.getSurname() + "\u001B[0m");
            System.out.print(contact.formatPhoneList());
        }
        System.out.println("\u001B[34m" + "-----------------------------" + "\u001B[0m");
    }

    public boolean isEmpty() {
        return contactList.isEmpty();
    }
}
