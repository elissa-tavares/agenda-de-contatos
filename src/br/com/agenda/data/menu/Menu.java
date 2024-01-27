package br.com.agenda.data.menu;

import br.com.agenda.data.base.ContactList;
import br.com.agenda.functionalities.InsertAndModify;

import java.util.Scanner;

public class Menu {
    public static void displayMainMenu() {
        System.out.println(">>>> Menu <<<<\n" + "1 - Listar Contatos\n" + "2 - Adicionar Contato\n" + "3 - Remover Contato\n" + "4 - Editar Contato\n" + "5 - Sair\n");
        System.out.print("Digite uma opção: ");
    }

    public static boolean checkOptionsMenu(long option, Scanner scanner, ContactList contactList) {
        InsertAndModify action = new InsertAndModify();
        switch ((int) option) {
            case 1:
                contactList.displayList();
                break;
            case 2:
                action.addContact(scanner, contactList);
                break;
            case 3:
                action.rmContact(contactList, scanner);
                break;
            case 4:
                System.out.println("edita");
                break;
            case 5:
                return false;
        }
        return true;
    }

    public static boolean validateEntry(long option) {
        option = (int) option;

        if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5) {
            return true;
        } else {
            System.out.println("\u001B[31m" + "Opção inválida" + "\u001B[0m"); //red
            return false;
        }
    }
    
}
