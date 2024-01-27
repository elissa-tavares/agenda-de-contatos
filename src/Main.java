import br.com.agenda.data.menu.Menu;
import br.com.agenda.data.base.ContactList;
import br.com.agenda.functionalities.InsertAndModify;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactList contactList = new ContactList();
        boolean performs = true, valid;
        Long option;

        while (performs) {
            Menu.displayMainMenu();
            try {
                option = scanner.nextLong();
                valid = Menu.validateEntry(option);
                if (valid) {
                    performs = Menu.checkOptionsMenu(option, scanner, contactList);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\u001B[31m" + "Erro: Você deve digitar um número inteiro." + "\u001B[0m");
            }
        }
        scanner.close();
    }
}