package br.com.agenda.service;

import br.com.agenda.model.Telephone;
import br.com.agenda.repository.ContactRepository;
import br.com.agenda.model.Contact;

public class ContactService {

    static ContactRepository contactRepository;
    static TelephoneService telephoneService;

    public ContactService() {
        contactRepository = new ContactRepository();
        telephoneService = new TelephoneService();
    }


    public boolean listNotFound(int option) {
        return ((option == 1) || (option == 3) || (option == 4)) && contactRepository.isEmpty();
    }

    public boolean phoneBookNotFound(int option, Long contactId) {
        return ((option == 2) || (option == 3)) && contactRepository.emptyPhoneBook(contactId);
    }

    public String displayList() {
        return contactRepository.displayList();
    }

    public String phoneDisplayList(Long contactId) {
        return contactRepository.phoneDisplayList(contactId);
    }

    public boolean validateContactId(Long contactId) {
        return contactRepository.contactIdVerification(contactId);
    }

    public Long setContactId() {
        return contactRepository.isEmpty() ? 0L : contactRepository.nextContactId();
    }

    public void create(Contact contact, Telephone telephone) {
        contact.setId(setContactId());
        telephone.setId(0L);
        contact.addPhone(telephone);
        contactRepository.save(contact);
    }

    public void updateNumber(Long contactId, Long phoneId, Telephone newNumber) {
        Telephone phone = contactRepository.getPhone(contactId, phoneId);
        //implementa regras de negocio aqui parar comprar se o telefone igual
        contactRepository.updatePhone(contactId, phoneId, newNumber);
    }

    public void updateName(Long contactId, Contact newContact) {
        Contact contact = contactRepository.getContact(contactId);
        //implementa regras de negocio aqui parar comprar se o telefone igual
        contactRepository.updateName(contactId, newContact);
    }

    public void delete(Long id) {
        contactRepository.delete(id);
    }

    public void createTelephone(Long contactId, Telephone telephone) {
        telephone.setId(telephoneService.setId(contactId));
        contactRepository.savePhone(contactId, telephone);
    }

    public void deleteTelephone(Long contactId, Long idTelephone) {
        contactRepository.deletePhone(idTelephone, contactId);
    }

}