package BWINF;

import java.util.ArrayList;

/**
 * Created by 05021712 on 07.11.2017.
 */
public class main {
    int anzJugendliche;
    int anzErwachsene;


    public static void main(String[] args) {
        initialisierenMat1();
    }

    private static void initialisierenMat1() {
        ArrayList<Person> besucher = new ArrayList<Person>();

        Person p;

        //Schulklasse 26 Schüler
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);

        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);

        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);


        //Lehrerin
        p = new Person(false, true);
        besucher.add(p);

        Buchung b;
        b = new Buchung(besucher, true, false, 3);

/*
        //*****************************************************************************************************************************************************************
        besucher.clear();

        //Schulklasse 26 Schüler
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);

        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);

        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);


        //Lehrerin
        p = new Person(false, true);
        besucher.add(p);

        Buchung altB;
        altB = new Buchung(besucher, true,true, 3)
        */
    }

    private static void initStutzenberg() {
        ArrayList<Person> besucher = new ArrayList<Person>();

        Person p;

        //5 Kinder
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);
        p = new Person(true, false);
        besucher.add(p);


        //4 Erwachsene
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);

        Buchung b;
        b = new Buchung(besucher, true, true, 0);


        besucher.clear();
        //***************************************************************************************************************************************************************************

        //5 Kinder
        p = new Person(false, false);
        besucher.add(p);
        p = new Person(false, false);
        besucher.add(p);
        p = new Person(false, false);
        besucher.add(p);
        p = new Person(false, false);
        besucher.add(p);
        p = new Person(false, false);
        besucher.add(p);


        //4 Erwachsene
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);
        p = new Person(false, true);
        besucher.add(p);

        Buchung b1;
        b1 = new Buchung(besucher, false, true, 0);

    }
}
