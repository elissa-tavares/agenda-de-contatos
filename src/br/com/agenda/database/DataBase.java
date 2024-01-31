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

    public void createPhone(Long contactId, Telephone Phone) {
        Contact contact = read(contactId);
        contact.addPhone(Phone);
    }

    public Contact read(Long id) {
        return contactList.stream()
                .filter(contact -> Objects.equals(id, contact.getId()))
                .findFirst().get();
    }

    public void update() {

    }

    public void delete(Long id) {
        Contact contact = read(id);
        contactList.remove(contact);
    }

    public void deletePhone(Long contactId, Long phoneId) {
        Contact contact = read(contactId);
        Telephone telephone = contact.getPhones().stream()
                .filter(phone -> Objects.equals(phoneId, phone.getId()))
                .findFirst().get();
        contact.getPhones().remove(telephone);
    }

    public boolean isEmpty() {
        return contactList.isEmpty();
    }

    public boolean emptyPhoneBook(Long id) {
        Contact contact = read(id);
        return contact.getPhones().isEmpty();
    }

    public String displayList() {
        StringBuilder Formatted = new StringBuilder();
        Formatted.append(Color.BLUE).append("----------- AGENDA ----------\n").append(Color.RESET);
        for (Contact contact : contactList) {
            Formatted.append(Color.BLUE).append(contact.getId()).append(" | ").append(" ").append(contact.getName()).append(" ").append(contact.getSurname()).append("\n").append(Color.RESET);
            Formatted.append(phoneList(contact));
        }
        Formatted.append(Color.BLUE).append("-----------------------------\n").append(Color.RESET);
        return Formatted.toString();
    }

    public String phoneList(Contact contact) {
        StringBuilder Formatted = new StringBuilder();
        for (Telephone element : contact.getPhones()) {
            Formatted.append(element.getId()).append(" | ").append(" (").append(element.getDdd()).append(")").append(element.getNumber()).append("\n"); //add yellow
        }
        return Formatted.toString();
    }

    public boolean validNumber(String ddd, long number) {
        return contactList.stream()
                .flatMap(contact -> contact.getPhones().stream())
                .anyMatch(phone -> phone.getDdd().equals(ddd) && phone.getNumber().equals(number));
    }

    public boolean validContactId(Long id) {
        return contactList.stream()
                .anyMatch(contact -> Objects.equals(id, contact.getId()));
    }

    public boolean validPhoneId(Long contactId, Long phoneId) {
        Contact contact = read(contactId);
        return contact.getPhones().stream()
                .anyMatch(telephone -> Objects.equals(phoneId, telephone.getId()));
    }

    public Long nextContactId() {
        Contact contact = contactList.getLast();
        return contact.getId() + 1L;
    }

    public Long nextPhoneId(Long contactId) {
        Contact contact = read(contactId);
        return contact.getPhones().getLast().getId() + 1L;
    }
}
