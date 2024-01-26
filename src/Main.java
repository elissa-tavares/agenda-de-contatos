import informacoes.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean performs = true, valid;
        ArrayList<Contact> contactList = new ArrayList<>();
        Long option;

        while (performs) {
            menu();
            try {
                option = scanner.nextLong();
                valid = validateEntry(option);
                if (valid) {
                    performs = checkOptionsMenu(option, scanner, contactList);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(red() + "Erro: Você deve digitar um número inteiro." + resetColor());
            }
        }
        scanner.close();
    }

    public static void addContact(Scanner scanner, List<Contact> contactList) {
        scanner.nextLine(); //clear buffer
        Contact newContact = new Contact();

        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        contactList.add(newContact);
    }

    public static boolean checkOptionsMenu(long option, Scanner scanner, List<Contact> contactList) {
        switch ((int) option) {
            case 1:
                addContact(scanner, contactList);
                break;
            case 2:
                System.out.println("remove");
                break;
            case 3:
                System.out.println("edita");
                break;
            case 4:
                return false;
        }
        return true;
    }

    public static boolean validateEntry(long option) {
        option = (int) option;

        if (option == 1 || option == 2 || option == 3 || option == 4) {
            return true;
        } else {
            System.out.println(red() + "Opção inválida" + resetColor());
            return false;
        }
    }

    public static void menu() {
        System.out.println(">>>> Menu <<<<\n" + "1 - Adicionar Contato\n" + "2 - Remover Contato\n" + "3 - Editar Contato\n" + "4 - Sair\n");
        System.out.print("Digite uma opção: ");
    }

    //Color modification functions
    public static String red() {
        return "\u001B[31m";
    }

    public static String resetColor() {
        return "\u001B[0m";
    }
}