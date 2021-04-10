package io.osvaldocabral.recyclerviewapp;

import java.util.ArrayList;

public class DataSingleton {

    public static DataSingleton instance = new DataSingleton();


    private DataSingleton() {
        listAgenda.add(new Agenda("Osvaldo", "+55 41 9 8840-8809", "Rua 123"));
        listAgenda.add(new Agenda("Jhonatan", "+55 41 9 9109-0488", "Rua 321"));
    }


    public static DataSingleton getInstance() {
        return instance;
    }


    public ArrayList<Agenda> listAgenda = new ArrayList<>();


    public Agenda currentEditAgenda;

}
