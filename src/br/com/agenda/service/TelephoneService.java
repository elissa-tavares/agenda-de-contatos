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

    public boolean validatePhoneId(Long idTelephone, Long idContact) {
        return contactRepository.verificationIdTelephone(idTelephone, idContact);
    }

    public Long setId(Long idContact) {
        return contactRepository.emptyPhoneBook(idContact) ? 0L : contactRepository.nextPhoneId(idContact);
    }

}
