package br.com.agenda.database;

import java.util.ArrayList;

import br.com.agenda.model.Contact;
import br.com.agenda.model.Telephone;

public class Converter {

    public Contact getContact(String line){
        String[] objects = line.split("/");

        Contact contact = new Contact();
        contact.setPhones(new ArrayList<>());

        for(int i = 0; i < objects.length; i++){
            String[] values = objects[i].split(";");

            if(i == 0){
                contact.setId(Long.parseLong(values[0]));
                contact.setName(values[1]);
                contact.setSurname(values[2]);
            }
            else {
                Telephone telephone = new Telephone(values[1], Long.parseLong(values[2]));
                telephone.setId(Long.parseLong(values[0]));
                contact.getPhones().add(telephone);
            }
        }
        return contact;
    }
}
