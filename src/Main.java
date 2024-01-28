import br.com.agenda.data.menu.Menu;
import br.com.agenda.data.base.ContactList;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactList contactList = new ContactList();
        Menu menu = new Menu();
        boolean performs = true, valid;
        Long option;

        while (performs) {
            menu.displayMenu();
            try {
                option = scanner.nextLong();
                scanner.nextLine();
                valid = menu.validateEntry(option);
                if (valid) {
                    performs = menu.checkOptionsMenu(option, scanner, contactList);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("\u001B[31m" + "Erro: Você deve digitar um número inteiro." + "\u001B[0m");
            }
        }
        scanner.close();
    }
}