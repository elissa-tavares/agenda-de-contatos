package br.com.agenda.data.base;

import br.com.agenda.details.contact.Contact;
import br.com.agenda.details.telephone.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactList {
    private List<Contact> contactList = new ArrayList<>();

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public void displayList() {
        System.out.println("\u001B[34m" + "----------- AGENDA ----------" + "\u001B[0m");
        for (Contact contact : contactList) {
            System.out.print("\u001B[34m"); //blue
            System.out.println(contact.getId() + "\u001B[34m" + " | " + " " + contact.getName() + " " + contact.getSurname() + "\u001B[0m");
            System.out.print(contact.formatPhoneList());
        }
        System.out.println("\u001B[34m" + "-----------------------------" + "\u001B[0m");
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

    public Long nextIdContact() {
        return contactList.getLast().getId() + 1L;
    }

    public boolean emptyTelephoneList(Long id) {
        System.out.println("entrou no emptyTelephoneList");
        return contactList.get(Math.toIntExact(id)).getTelephones().isEmpty();
    }

    public Long nextIdTelephone(Long id) {
        System.out.println("entrou no nextIdTelephone");
        return contactList.get(Math.toIntExact(id)).getTelephones().getLast().getId() + 1L;
    }
}
