package br.com.agenda.service;

import br.com.agenda.repository.ContactRepository;

public class TelephoneService {
    static ContactRepository contactRepository;

    public TelephoneService() {
        contactRepository = new ContactRepository();
    }

    public boolean validNumber(String ddd, Long number) {
        return contactRepository.checkRepeatedNumbers(ddd, number);
    }

    public boolean validatePhoneId(Long phoneId, Long contactId) {
        return contactRepository.phoneIdVerification(phoneId, contactId);
    }

    public Long setId(Long contactId) {
        return contactRepository.emptyPhoneBook(contactId) ? 0L : contactRepository.nextPhoneId(contactId);
    }

    public String formatsDDD(String ddd) {
        return ddd.replaceAll("[^0-9-]", "");
    }

    public boolean invalidDDD(String ddd){
        return ddd.isEmpty();
    }
}
