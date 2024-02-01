package br.com.agenda.model;

import java.util.ArrayList;
import java.util.List;

public class Contact {
    private Long id;
    private String name;
    private String surname;
    private final List<Telephone> phones = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<Telephone> getPhones() {
        return phones;
    }

    public void addPhone(Telephone newTelephone) {
        this.phones.add(newTelephone);
    }

}