package br.com.agenda.details;

import br.com.agenda.data.base.ContactList;

public class Telephone {
    private Long id;
    private String ddd;
    private Long number;


    public Long getId() {
        return id;
    }

    public void setId(ContactList contactList, Long contactId) {
        this.id = contactId == null || contactList.emptyTelephoneList(contactId) ? 0L : contactList.nextIdTelephone(contactId);
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        ddd = ddd.replaceAll(" ", "");
        ddd = ddd.replaceAll("\n", "");
        this.ddd = ddd;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}