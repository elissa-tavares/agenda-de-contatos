package br.com.agenda.service;

import br.com.agenda.controller.Color;
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


    public void create(Contact contact, Telephone telephone) {
        contact.setId(setContactId());
        telephone.setId(0L);
        contact.addPhone(telephone);
        contactRepository.save(contact);
    }

    public void createPhone(Long contactId, Telephone telephone) {
        telephone.setId(telephoneService.setId(contactId));
        contactRepository.savePhone(contactId, telephone);
        System.out.println(Color.GREEN + "Telephone adicionado com sucesso" + Color.RESET);
    }

    public void updateNumber(Long contactId, Long phoneId, Telephone newNumber) {
        contactRepository.updatePhone(contactId, phoneId, newNumber);
        System.out.println(Color.GREEN + "Telefone atualizado com sucesso" + Color.RESET);
    }

    public void updateName(Long contactId, Contact newContact) {
        Contact contact = contactRepository.getContact(contactId);

        if (!invalidName(newContact)) {
            if (contact.getName().equals(newContact.getName()) && contact.getSurname().equals(newContact.getSurname())) {
                System.out.println(Color.RED + "Os nomes são iguais. Tente novamente" + Color.RESET);
                System.out.println(Color.YELLOW + "Informações salvas" + Color.RESET);
                System.out.println(contact.getName() + " " + contact.getSurname());
                System.out.println(Color.YELLOW + "Informações inseridas" + Color.RESET);
                System.out.println(newContact.getName() + " " + newContact.getSurname());
                return;
            }
            contactRepository.updateName(contactId, newContact);
            System.out.println(Color.GREEN + "Nome atualizado com sucesso" + Color.RESET);
        }
    }

    public void delete(Long id) {
        contactRepository.delete(id);
    }

    public void deletePhone(Long contactId, Long idTelephone) {
        contactRepository.deletePhone(idTelephone, contactId);
        System.out.println(Color.GREEN + "Telefone removido com sucesso" + Color.RESET);
    }

    public boolean listNotFound(int option) {
        return ((option == 1) || (option == 3) || (option == 4)) && contactRepository.isEmpty();
    }

    public boolean phoneBookNotFound(int option, Long contactId) {
        return ((option == 2) || (option == 3)) && contactRepository.emptyPhoneBook(contactId);
    }

    public boolean invalidName(Contact contact) {
        boolean invalid = (contact.getName().isBlank() && contact.getSurname().isBlank());
        if (invalid) {
            System.out.println(Color.RED + "Os campos estão vazios. Tente novamente" + Color.RESET);
        }
        return invalid;
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

}