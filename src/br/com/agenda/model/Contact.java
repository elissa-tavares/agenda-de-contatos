package br.com.agenda.model;

import br.com.agenda.repository.ContactRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contact {
    private Long id;
    private String name;
    private String surname;
    private final List<Telephone> telephones = new ArrayList<>();

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

    public List<Telephone> getTelephones() {
        return telephones;
    }

    public void addTelephones(Telephone newTelephone) {
        this.telephones.add(newTelephone);
    }

}