import br.com.agenda.controller.ContactController;

public class Agenda {
    public static void main(String[] args) {
        ContactController controller = new ContactController();

        int option;
        do {
            option = controller.menu();
            switch (option) {
                case 1 -> controller.read();
                case 2 -> controller.create();
                case 3 -> controller.delete();
                case 4 -> controller.update();
                case 5 -> controller.terminate();
                default -> controller.error();
            }
        } while (option != 5);
    }
}