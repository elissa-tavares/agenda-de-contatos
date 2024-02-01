package br.com.agenda.model;

public class Telephone {
    private Long id;
    private String ddd;
    private Long number;

    public Telephone(String ddd, Long number) {
        this.ddd = ddd;
        this.number = number;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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