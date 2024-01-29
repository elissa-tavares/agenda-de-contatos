package br.com.agenda.data.base;

import br.com.agenda.details.Contact;
import br.com.agenda.details.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ContactList {
    private final List<Contact> contactList = new ArrayList<>();

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public boolean emptyTelephoneList(Long id) {
        Contact contact = contactList.get(Math.toIntExact(id));
        return contact.emptyTelephoneList();
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

    public void phoneDisplayList(Long idContact) {
        Contact contact = contactList.get(Math.toIntExact(idContact));
        System.out.println(contact.getId() + "\u001B[34m" + " | " + " " + contact.getName() + " " + contact.getSurname() + "\u001B[0m");
        System.out.print(contact.formatPhoneList());
    }

    public boolean checkRepeatedNumbers(String ddd, long number) {
        if (ddd.isEmpty()) {
            System.out.println("\u001B[31m" + "DDD inválido" + "\u001B[0m");
            return false;
        }
        for (Contact contact : contactList) {
            if (!contact.validNumber(ddd, number)) {
                return false;
            }
        }
        return true;
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
                break;
            }
        }
    }

    public void addTelephoneContact(Telephone telephone, Long idContact) {
        for (Contact contact : contactList) {
            if (Objects.equals(idContact, contact.getId())) {
                contact.addTelephones(telephone);
                System.out.println("\u001B[32m" + "Telefone adicionado com sucesso\n" + "\u001B[0m"); //green
                break;
            }
        }
    }

    public void rmTelephoneContact(Long idTelephone, Long idContact) {
        Contact contact = contactList.get(Math.toIntExact(idContact));
        contact.removeTelephones(idTelephone, contact);
    }

    public void editTelephoneContact(Long idTelephone, Telephone edited, Long idContact) {
        Contact contact = contactList.get(Math.toIntExact(idContact));
        contact.editTelephone(idTelephone, edited.getDdd(), edited.getNumber());
    }

    public void editNameContact(Contact edited, Long idContato) {
        for (Contact contact : contactList) {
            if (Objects.equals(idContato, contact.getId())) {
                contact.editName(edited.getName(), edited.getSurname());
                break;
            }
        }
    }

    public boolean verificationIdContact(Long id) {
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

    public boolean verificationIdTelephone(Long id, long idContact) {
        Contact contact = contactList.get(Math.toIntExact(idContact));
        return contact.checkPhoneId(id);
    }

    public Long nextIdTelephone(Long id) {
        Contact contact = contactList.get(Math.toIntExact(id));
        return contact.nextIdTelephone();
    }
}