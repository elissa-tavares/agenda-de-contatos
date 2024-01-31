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

    public boolean phoneBookNotFound(int option, Long idContact) {
        return ((option == 2) || (option == 3)) && contactRepository.emptyPhoneBook(idContact);
    }

    public String displayList() {
        return contactRepository.displayList();
    }

    public String phoneDisplayList(Long idContact){
        return contactRepository.phoneDisplayList(idContact);
    }

    public boolean validateContactId(Long idContact) {
        return contactRepository.verificationIdContact(idContact);
    }

    public Long setIdContact() {
        return contactRepository.isEmpty() ? 0L : contactRepository.nextContactId();
    }

    public void create(Contact contact, Telephone telephone) {
        contact.setId(setIdContact());
        telephone.setId(0L);
        contact.addTelephones(telephone);
        contactRepository.save(contact);
    }

    public void delete(Long id) {
        contactRepository.delete(id);
    }

    public void createTelephone(Long idContact, Telephone telephone) {
        telephone.setId(telephoneService.setId(idContact));
        contactRepository.saveTelephone(idContact, telephone);
    }

    public void deleteTelephone(Long idContact, Long idTelephone) {
        contactRepository.deleteTelephone(idTelephone, idContact);
    }

//    public void updateNumber(Long idTelephone, Long idContact) {
//        if (contactRepository.emptyPhoneBook(idContact)) {
//            System.out.println(Color.YELLOW + "Não há nenhum telefone registrado nesse contato" + Color.RESET);
//        }
//        //caso contrario edita aqui
//
//    }

//    public void updateName(String[] fullName, Long idContact) {
//        contactRepository.updateName(fullName[0], fullName[1], idContact);
//    }

//    public void terminate() {
//        //essa funcao vai servir pra salvar a lista no arquivo de texto
//    }


}