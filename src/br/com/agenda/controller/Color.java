package br.com.agenda.controller;

public enum Color {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),

    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m");

    private final String codeANSI;

    Color(String codeANSI) {
        this.codeANSI = codeANSI;
    }

    @Override
    public String toString() {
        return codeANSI;
    }
}
