package br.com.agenda.repository;

import br.com.agenda.database.DataBase;
import br.com.agenda.model.Contact;
import br.com.agenda.model.Telephone;


public class ContactRepository {

    static DataBase database;

    public ContactRepository() {
        database = new DataBase();
    }

    public boolean isEmpty() {
        return database.isEmpty();
    }

    public boolean emptyPhoneBook(Long id) {
        return database.emptyPhoneBook(id);
    }

    public String displayList() {
        return database.displayList();
    }

    public String phoneDisplayList(Long contactId) {
        return database.phoneList(database.readContact(contactId));
    }

    public Long nextContactId() {
        return database.nextContactId();
    }

    public Long nextPhoneId(Long contactId) {
        return database.nextPhoneId(contactId);
    }

    public boolean checkRepeatedNumbers(String ddd, long number) {
        return database.validNumber(ddd, number);
    }

    public void save(Contact newContact) {
        database.create(newContact);
    }

    public Telephone getPhone(Long contactId, Long phoneId) {
        return database.readPhone(contactId, phoneId);
    }

    public Contact getContact(Long contactId) {
        return database.readContact(contactId);
    }

    public void updatePhone(Long contactId, Long phoneId, Telephone newPhone) {
        database.updatePhone(contactId, phoneId, newPhone);
    }

    public void updateName(Long contactId, Contact newContact) {
        database.updateName(contactId, newContact);
    }

    public void delete(Long id) {
        database.delete(id);

    }

    public void savePhone(Long contactId, Telephone Phone) {
        database.createPhone(contactId, Phone);
    }

    public void deletePhone(Long idPhone, Long contactId) {
        database.deletePhone(contactId, idPhone);
    }

    public boolean contactIdVerification(Long id) {
        return database.validContactId(id);
    }

    public boolean phoneIdVerification(Long idPhone, long contactId) {
        return database.validPhoneId(contactId, idPhone);
    }

}