import java.util.Scanner;

public class Main {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args) {
        while (true) {
            menu();
            receiveMenuOption();
        }
    }

    public static void receiveMenuOption() {
        Scanner scanner = new Scanner(System.in);
        long option;
        boolean valid;

        try {
            System.out.print("Digite uma opção: ");
            option = scanner.nextLong();
            valid = option == 1 || option == 2 ||option == 3 ||option == 4;

            if(valid){
                checkOptionsMenu((int)option);
            } else {
                System.out.println(ANSI_RED + "Opção inválida" + ANSI_RESET);
                return;
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println(ANSI_RED + "Erro: Você deve digitar um número inteiro." + ANSI_RESET);
            return;
        }
    }


    public static void checkOptionsMenu(int option) {
        switch (option) {
            case 1:
                System.out.println("adiciona");
                break;
            case 2:
                System.out.println("remove");
                break;
            case 3:
                System.out.println("edita");
                break;
            case 4:
                System.exit(0);
                //Scanner.close();
                break;
        }
    }
    public static void menu() {
        System.out.println(
                ">>>> Menu <<<<\n" +
                        "1 - Adicionar Contato\n" +
                        "2 - Remover Contato\n" +
                        "3 - Editar Contato\n" +
                        "4 - Sair\n"
        );
    }
}