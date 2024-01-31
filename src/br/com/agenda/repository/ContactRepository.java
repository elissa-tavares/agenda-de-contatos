package br.com.agenda.repository;

import br.com.agenda.controller.Color;
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
                    Telephone telephone = new Telephone();
                    telephone.setIdDataBase(data[0]);
                    telephone.setDdd(data[1]);
                    telephone.setNumber(Long.valueOf(data[2]));
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

    public String phoneDisplayList(Long idContact) {
        return database.phoneList(database.read(idContact));
    }

    public Long nextContactId(){
        return database.nextContactId();
    }

    public Long nextPhoneId(Long idContact){
        return database.nextPhoneId(idContact);
    }

    public boolean checkRepeatedNumbers(String ddd, long number) {
        return database.validNumber(ddd, number);
    }

    public void save(Contact newContact) {
        database.create(newContact);
        System.out.println(Color.GREEN + "Contato adicionado com sucesso\n" + Color.RESET);
    }

    public void delete(Long id) {
        database.delete(id);
        System.out.println(Color.GREEN + "Contato removido com sucesso\n" + Color.RESET);

    }

    public void saveTelephone(Long idContact, Telephone telephone) {
        database.createTelephone(idContact, telephone);
    }

    public void deleteTelephone(Long idTelephone, Long idContact) {
        database.deleteTelephone(idContact, idTelephone);
    }

    public boolean verificationIdContact(Long id) {
        return database.validIdContact(id);
    }

    public boolean verificationIdTelephone(Long idTelephone, long idContact) {
        return database.validIdTelephone(idContact, idTelephone);
    }

//    public void updateTelephone(Long idTelephone, Telephone edited, Long idContact) {
//        Contact contact = database.get(Math.toIntExact(idContact));
//        contact.editTelephone(idTelephone, edited.getDdd(), edited.getNumber());
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
//    public void editTelephone(Long idTelephone, String ddd, Long number) {
//        for (Telephone telephone : this.telephones) {
//            if (Objects.equals(idTelephone, telephone.getId())) {
//                telephone.setDdd(ddd);
//                telephone.setNumber(number);
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