package br.com.agenda.details.telephone;

import br.com.agenda.data.base.ContactList;

public class Telephone {
    private Long id;
    private String ddd;
    private Long number;


    public Long getId() {
        return id;
    }

    public void setId(ContactList contactList, Long contactId) {
        if (!(contactList == null)) {
            this.id = !contactList.emptyTelephoneList(contactId) ? contactList.nextIdTelephone(contactId) : 0L;
        } else {
            this.id = 0L;
        }
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
