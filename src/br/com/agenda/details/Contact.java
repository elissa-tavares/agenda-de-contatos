package br.com.agenda.details;

import br.com.agenda.data.base.ContactList;

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

    public void setId(ContactList contactList) {
        this.id = !contactList.isEmpty() ? contactList.nextIdContact() : 0L;
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

    public boolean emptyTelephoneList() {
        return this.telephones.isEmpty();
    }

    public void addTelephones(Telephone newTelephone) {
        this.telephones.add(newTelephone);
    }

    public void removeTelephones(Long idTelephone, Contact contact) {
        for (Telephone telephone : contact.telephones) {
            if (Objects.equals(idTelephone, telephone.getId())) {
                telephones.remove(telephone);
                System.out.println("\u001B[32m" + "Telefone removido com sucesso\n" + "\u001B[0m"); //green
                break;
            }
        }
    }

    public boolean validNumber(String ddd, Long number) {
        for (Telephone telephone : this.telephones) {
            if (telephone.getDdd().equals(ddd) && telephone.getNumber().equals(number)) {
                System.out.println("\u001B[31m" + "Contato já existente" + "\u001B[0m");
                return false;
            }
        }
        return true;
    }

    public void editTelephone(Long idTelephone, String ddd, Long number) {
        for (Telephone telephone : this.telephones) {
            if (Objects.equals(idTelephone, telephone.getId())) {
                telephone.setDdd(ddd);
                telephone.setNumber(number);
                System.out.println("\u001B[32m" + "Telefone Editado com sucesso\n" + "\u001B[0m"); //green
                break;
            }
        }
    }

    public void editName(String name, String surname) {
        this.name = name;
        this.surname = surname;
        System.out.println("\u001B[32m" + "Nome editado com sucesso\n" + "\u001B[0m"); //green
    }

    public boolean checkPhoneId(Long id) {
        for (Telephone telephone : this.telephones) {
            if (Objects.equals(id, telephone.getId())) {
                return true;
            }
        }
        return false;
    }

    public Long nextIdTelephone() {
        return this.telephones.getLast().getId() + 1L;
    }

    public String formatPhoneList() {
        StringBuilder formated = new StringBuilder();
        for (Telephone element : this.telephones) {
            formated.append(element.getId()).append("\u001B[33m").append(" | ").append("\u001B[0m").append(" (").append(element.getDdd()).append(")").append(element.getNumber()).append("\n"); //add yellow
        }
        return formated.toString();
    }
}