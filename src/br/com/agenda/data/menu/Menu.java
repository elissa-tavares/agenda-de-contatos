package br.com.agenda.data.menu;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.functionalities.InsertAndModify;

import java.util.Scanner;

public class Menu {
    public void displayMenu() {
        System.out.println(">>>> Menu <<<<\n" + "1 - Listar Contatos\n" + "2 - Adicionar Contato\n" + "3 - Remover Contato\n" + "4 - Editar Contato\n" + "5 - Sair\n");
        System.out.print("Digite uma opção: ");
    }

    public boolean checkOptionsMenu(long option, Scanner scanner, ContactList contactList) {
        InsertAndModify action = new InsertAndModify();

        if (contactList.isEmpty() && ((option != 2) && (option != 5))) { //if it is different from insert and exit
            System.out.println("\u001B[33m" + "Agenda de contatos vazia" + "\u001B[0m\n");
            return true;
        }
        switch ((int) option) {
            case 1:
                contactList.displayList();
                break;
            case 2:
                contactList.addDataBase(action.inputContact(scanner, contactList));
                break;
            case 3:
                contactList.rmDataBase(action.idContactRemoved(scanner, contactList));
                break;
            case 4:
                action.editContact(scanner, contactList);
                break;
            case 5:
                return false;
        }
        return true;
    }

    public boolean validateEntry(long option) {
        option = (int) option;

        if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5) {
            return true;
        } else {
            System.out.println("\u001B[31m" + "Opção inválida" + "\u001B[0m"); //red
            return false;
        }
    }
}
