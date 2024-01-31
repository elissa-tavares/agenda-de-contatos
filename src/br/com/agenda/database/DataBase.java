package br.com.agenda.database;

import br.com.agenda.controller.Color;
import br.com.agenda.model.Contact;
import br.com.agenda.model.Telephone;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataBase {

    private final List<Contact> contactList;

    public DataBase() {
        contactList = new ArrayList<>();
    }


    public void create(Contact contact) {
        contactList.add(contact);
    }

    public void createTelephone(Long idContact, Telephone telephone) {
        Contact contact = read(idContact);
        contact.addTelephones(telephone);
        System.out.println(Color.GREEN + "Telefone adicionado com sucesso\n" + Color.RESET);
    }

    public Contact read(Long id) {
        for (Contact contact : contactList) {
            if (Objects.equals(id, contact.getId())) {
                return contact;
            }
        }
        return null;
    }

    public void update() {

    }

    public void delete(Long id) {
        Contact contact = read(id);
        System.out.println(Color.GREEN + "Contato removido com sucesso\n" + Color.RESET);
        contactList.remove(contact);
    }

    public void deleteTelephone(Long idContact, Long idTelephone) {
        Contact contact = read(idContact);
        for (Telephone telephone : contact.getTelephones()) {
            if (Objects.equals(idTelephone, telephone.getId())) {
                contact.getTelephones().remove(telephone);
                System.out.println(Color.GREEN + "Telefone removido com sucesso\n" + Color.RESET);
                break;
            }
        }
    }

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public boolean emptyPhoneBook(Long id) {
        Contact contact = read(id);
        return contact.getTelephones().isEmpty();
    }

    public String displayList() {
        StringBuilder Formatted = new StringBuilder();
        Formatted.append(Color.BLUE + "----------- AGENDA ----------\n" + Color.RESET);
        for (Contact contact : contactList) {
            Formatted.append(Color.BLUE).append(contact.getId()).append(" | ").append(" ").append(contact.getName()).append(" ").append(contact.getSurname()).append("\n").append(Color.RESET);
            Formatted.append(phoneList(contact));
        }
        Formatted.append(Color.BLUE + "-----------------------------\n" + Color.RESET);
        return Formatted.toString();
    }

    public String phoneList(Contact contact) {
        StringBuilder Formatted = new StringBuilder();
        for (Telephone element : contact.getTelephones()) {
            Formatted.append(element.getId()).append(" | ").append(" (").append(element.getDdd()).append(")").append(element.getNumber()).append("\n"); //add yellow
        }
        return Formatted.toString();
    }

    public boolean validNumber(String ddd, long number) {
        for (Contact contact : contactList) {
            for (Telephone telephone : contact.getTelephones()) {
                if (telephone.getDdd().equals(ddd) && telephone.getNumber().equals(number)) {
                    System.out.println(Color.RED + "Contato já existente" + Color.RESET);
                    return false;
                }
            }
        }
        return true;
    }

    public boolean validIdContact(Long id) {
        for (Contact contact : contactList) {
            if (Objects.equals(id, contact.getId())) {
                return true;
            }
        }
        return false;
    }

    public boolean validIdTelephone(Long idContact, Long idTelephone) {
        Contact contact = read(idContact);
        for (Telephone telephone : contact.getTelephones()) {
            if (Objects.equals(idTelephone, telephone.getId())) {
                return true;
            }
        }
        return false;
    }

    public Long nextContactId(){
        Contact contact = contactList.getLast();
        return contact.getId() + 1L;
    }
    public Long nextPhoneId(Long idContact){
        Contact contact = read(idContact);
        return contact.getTelephones().getLast().getId() + 1L;
    }
}
