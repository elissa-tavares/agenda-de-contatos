import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean performs = true, valid;
        Long option;

        while (performs) {
            menu();
            try {
                option = scanner.nextLong();
                valid = validateEntry(option);
                if (valid) {
                    performs = checkOptionsMenu(option);
                }
            } catch (Exception e) {
                String buffer = scanner.nextLine();
                System.out.println(red() + "Erro: Você deve digitar um número inteiro." + resetColor());
            }
        }
        scanner.close();
    }
    public static void menu() {
        System.out.println(
                ">>>> Menu <<<<\n" +
                        "1 - Adicionar Contato\n" +
                        "2 - Remover Contato\n" +
                        "3 - Editar Contato\n" +
                        "4 - Sair\n"
        );
        System.out.print("Digite uma opção: ");
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
    public static boolean checkOptionsMenu(long option) {
        switch ((int) option) {
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
                return false;
        }
        return true;
    }


    //Color modification functions
    public static String red(){
        return "\u001B[31m";
    }
    public static String resetColor(){
        return "\u001B[0m";
    }
}