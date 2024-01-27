import br.com.agenda.data.menu.Menu;
import br.com.agenda.data.base.ContactList;
import br.com.agenda.functionalities.InsertAndModify;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InsertAndModify action = new InsertAndModify();
        ContactList contactList = new ContactList();
        boolean performs = true, valid;
        Menu menu = new Menu();
        Long option;
        Long numId = 0L;

        while (performs) {
            menu.displayMainMenu();
            try {
                option = scanner.nextLong();
                valid = menu.validateEntry(option);
                if (valid) {
                    performs = menu.checkOptionsMenu(option, scanner, contactList, numId, action);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(menu.red() + "Erro: Você deve digitar um número inteiro." + menu.resetColor());
            }
            numId++; //tratar erro
        }
        scanner.close();
    }

}