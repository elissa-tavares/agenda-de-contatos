package informacoes;

import java.util.List;
import java.util.Scanner;

public class Contact {
    private Long id;
    private String name;
    private String surname;

    //private List<informacoes.Telephone> telephones;

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

//    public List<informacoes.Telephone> getTelephones() {
//        return telephones;
//    }
//
//    public void setTelephones(List<informacoes.Telephone> telephones) {
//        this.telephones = telephones;
//    }
}
