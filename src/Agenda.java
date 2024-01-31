import br.com.agenda.controller.ContactController;

import java.io.FileNotFoundException;

public class Agenda {
    public static void main(String[] args) throws FileNotFoundException {
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
            }
        } while (option != 5);
    }
}