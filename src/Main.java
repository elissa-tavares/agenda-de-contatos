import br.com.agenda.data.contact.Contact;
import br.com.agenda.data.telephone.Telephone;
import br.com.agenda.data.base.ContactList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean performs = true, valid;
        ContactList contactList = new ContactList();
        Long option;
        Long numId = 0L;

        while (performs) {
            menu();
            try {
                option = scanner.nextLong();
                valid = validateEntry(option);
                if (valid) {
                    performs = checkOptionsMenu(option, scanner, contactList, numId);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(red() + "Erro: Você deve digitar um número inteiro." + resetColor());
            }
            numId++; //tratar erro
        }
        scanner.close();
    }


    public static void menu() {
        System.out.println(">>>> Menu <<<<\n" + "1 - Listar Contatos\n" + "2 - Adicionar Contato\n" + "3 - Remover Contato\n" + "4 - Editar Contato\n" + "5 - Sair\n");
        System.out.print("Digite uma opção: ");
    }

    public static boolean validateEntry(long option) {
        option = (int) option;

        if (option == 1 || option == 2 || option == 3 || option == 4 || option == 5) {
            return true;
        } else {
            System.out.println(red() + "Opção inválida" + resetColor());
            return false;
        }
    }

    public static boolean checkOptionsMenu(long option, Scanner scanner, ContactList contactList, Long numId) {
        switch ((int) option) {
            case 1:
                contactList.displayList();
                break;
            case 2:
                addContact(scanner, contactList, numId);
                break;
            case 3:
                System.out.println("remove");
                break;
            case 4:
                System.out.println("edita");
                break;
            case 5:
                return false;
        }
        return true;
    }

    public static void addContact(Scanner scanner, ContactList contactList, Long numId) {
        Contact newContact = new Contact();

        newContact.setId(numId);

        scanner.nextLine(); //clear buffer
        System.out.print("Nome: ");
        newContact.setName(scanner.nextLine());

        System.out.print("Sobrenome: ");
        newContact.setSurname(scanner.nextLine());

        addNumberTelephone(scanner, newContact);
        contactList.addDataBase(newContact);
    }

    public static void addNumberTelephone(Scanner scanner, Contact newContact){
        Telephone newTelephone = new Telephone();

        System.out.print("Digite o DDD: ");
        newTelephone.setDdd(scanner.nextLine());

        System.out.print("Digite o numero: ");
        newTelephone.setNumber(scanner.nextLong());

        newContact.addTelephones(newTelephone);
    }

    public static String red() {
        return "\u001B[31m";
    }

    public static String resetColor() {
        return "\u001B[0m";
    }
}