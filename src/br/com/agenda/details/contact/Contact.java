package br.com.agenda.details.contact;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.details.telephone.Telephone;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Long id;
    private String name;
    private String surname;
    private List<Telephone> telephones = new ArrayList<Telephone>();

    public Long getId() {
        return id;
    }

    public void setId(ContactList contactList) {
        Long id = contactList.lastId() + 1L;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSurname() {
        return surname;
    }

    public void addTelephones(Telephone newTelephone) {
        this.telephones.add(newTelephone);
    }

    public String formatPhoneList() {
        String formated = "";
        for (Telephone element : this.telephones) {
            formated += ("(" + element.getDdd() + ")" + " " + element.getNumber() + "\u001B[33m" + "|" + "\u001B[0m\n"); //add yellow
        }
        return formated;
    }
}
