package br.com.agenda.repository;

import br.com.agenda.database.DataBase;
import br.com.agenda.model.Contact;
import br.com.agenda.model.Telephone;


public class ContactRepository {

    static DataBase database;

    public ContactRepository() {
        database = new DataBase();
    }


    /*public void lineConverterGet(String line){
            String[] objects = line.split("/");
            Contact newContact = new Contact();

            for(int i = 0; i < objects.length; i++){
                String[] data = objects[i].split(";");
                if(i == 0){
                    newContact.setIdDataBase(data[0]);
                    newContact.setName(data[1]);
                    newContact.setSurname(data[2]);
                }
                else {
                    Phone Phone = new Phone();
                    Phone.setIdDataBase(data[0]);
                    Phone.setDdd(data[1]);
                    Phone.setNumber(Long.valueOf(data[2]));
                }
            }
            save(newContact);
        }*/
    public boolean isEmpty() {
        return database.isEmpty();
    }

    public boolean emptyPhoneBook(Long id) {
        return database.emptyPhoneBook(id);
    }

    public String displayList() {
        return database.displayList();
    }

    public String phoneDisplayList(Long contactId) {
        return database.phoneList(database.read(contactId));
    }

    public Long nextContactId() {
        return database.nextContactId();
    }

    public Long nextPhoneId(Long contactId) {
        return database.nextPhoneId(contactId);
    }

    public boolean checkRepeatedNumbers(String ddd, long number) {
        return database.validNumber(ddd, number);
    }

    public void save(Contact newContact) {
        database.create(newContact);
    }

    public void delete(Long id) {
        database.delete(id);

    }

    public void savePhone(Long contactId, Telephone Phone) {
        database.createPhone(contactId, Phone);
    }

    public void deletePhone(Long idPhone, Long contactId) {
        database.deletePhone(contactId, idPhone);
    }

    public boolean contactIdVerification(Long id) {
        return database.validContactId(id);
    }

    public boolean phoneIdVerification(Long idPhone, long contactId) {
        return database.validPhoneId(contactId, idPhone);
    }

//    public void updatePhone(Long idPhone, Phone edited, Long contactId) {
//        Contact contact = database.get(Math.toIntExact(contactId));
//        contact.editPhone(idPhone, edited.getDdd(), edited.getNumber());
//    }
//
//    public void updateName(Contact edited, Long idContato) {
//        for (Contact contact : database) {
//            if (Objects.equals(idContato, contact.getId())) {
//                contact.editName(edited.getName(), edited.getSurname());
//                break;
//            }
//        }
//    }
//
//    public void editPhone(Long idPhone, String ddd, Long number) {
//        for (Phone Phone : this.Phones) {
//            if (Objects.equals(idPhone, Phone.getId())) {
//                Phone.setDdd(ddd);
//                Phone.setNumber(number);
//                System.out.println("\u001B[32m" + "Telefone Editado com sucesso\n" + "\u001B[0m"); //green
//                break;
//            }
//        }
//    }
//
//    public void editName(String name, String surname) {
//        this.name = name;
//        this.surname = surname;
//        System.out.println("\u001B[32m" + "Nome editado com sucesso\n" + "\u001B[0m"); //green
//    }

}