package BWINF;

import java.util.ArrayList;

/**
 * Created by 05021712 on 07.11.2017.
 * <p>
 * <p>
 * ToDo:
 * toString Methoden implementieren DONE
 * Fehler bei Unterscheidung von ErwachsenenEinzel und Jugendlichen Einzel beheben DONE
 * Fehler bei Gruppen / Familienkarten beheben Person als Berechnet kennzeichnen DONE
 * Vergleich der verschiedenen Varianten ( günstigste raussuchen) DONE
 * Vergleich Buchung ???? Verworfen
 * Gruppenkarten am Wochenende überprüfen
 */
public class Buchung {
    private ArrayList<Person> personen = new ArrayList<>();
    private ArrayList<Variante> varianten = new ArrayList<>();
    private Boolean isWochentags;

    private Boolean isInFerien;
    private int anzGutscheine = 0;

    public Buchung(ArrayList<Person> person, Boolean isWochentags, Boolean isInFerien, int anzGutscheine) {
        this.personen = person;
        this.isWochentags = isWochentags;
        this.isInFerien = isInFerien;
        this.anzGutscheine = anzGutscheine;

        alleVariantenBerechnen();
    }

    public Variante getGuenstigsteVariante() {
        Variante guenstigste = varianten.get(1);

        for (Variante v : varianten) {
            if (v.getPreis() < guenstigste.getPreis()) {
                guenstigste = v;
            }
        }
        return guenstigste;
    }

    public void alleVariantenBerechnen() {

        Variante var = null;
        int maxGruppen = 0;
        int maxFamilien = getAnzMaxFamilienkarten();

        if (isWochentags)
            maxGruppen = AnzUnberechneten() / 6;

        for (int i = 0; i <= maxFamilien + 1; i++) {
            for (int j = 0; j <= maxGruppen + 1; j++) {
                var = getNewVar(j, i);
                varianten.add(var);
            }
        }

        System.out.println("\n");

        guenstigsteAusgeben();
    }

    private void guenstigsteAusgeben() {
        Variante guenstigste = varianten.get(1);

        for (Variante v : varianten) {
            if (v.getPreis() < guenstigste.getPreis())
                guenstigste = v;
        }
        System.out.println(guenstigste.toString() + "***************************************************************************************************************************\n\n\n");
    }

    public Variante getNewVar(int gruppenK, int familienK) {
        Variante v = new Variante(this);

        v.berechnen(gruppenK, familienK);

        //System.out.println("Buchung: Neue Variante mit GK : " + gruppenK + " FK : " + familienK + " Preis as Int: " + v.getPreis());

        this.resetPersonenBerechnet();

        return v;
    }

    private void resetPersonenBerechnet() {
        for (Person p : personen) {
            p.reset();
        }
    }

    public Boolean hasErwachsenen() {
        for (Person p : personen) {
            if (p.isErwachsener() && !p.isBerechnet()) {
                return true;
            }
        }
        return false;
    }

    public Person getErwachsenen() {
        // Gibt eine Erwachsene unberechnete Person zurück
        Person back = null;
        for (Person p : personen) {
            if (p.isErwachsener() && !p.isBerechnet()) {
                back = p;
                break;
            }
        }
        return back;
    }

    public Person getJugendlichen() {
        //gibt eine jugendliche unberechnete Person zurück
        Person back = null;
        for (Person p : personen) {
            if (p.isJugendlicher() && !p.isBerechnet()) {
                back = p;
                break;
            }
        }
        return back;
    }

    public Person getUnberechneten() {
        Person p;

        if (hasErwachsenen())
            p = getErwachsenen();
        else
            p = getJugendlichen();

        return p;
    }

    public int getAnzErwachsene() {
        int anz = 0;

        for (Person p : personen) {
            if (p.isErwachsener())
                anz++;
        }
        return anz;
    }

    public int getAnzJugendliche() {
        int anz = 0;

        for (Person p : personen) {
            if (p.isJugendlicher())
                anz++;
        }
        return anz;
    }

    public int getAnzGutscheine() {
        return anzGutscheine;
    }

    public Boolean isWochentags() {
        return isWochentags;
    }

    public Boolean isInFerien() {
        return isInFerien;
    }

    public ArrayList<Person> getPersonen() {
        return personen;
    }

    public int anzUnberechnetErwachsene() {
        int anz = 0;
        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i).isErwachsener() && !personen.get(i).isBerechnet())
                anz++;
        }
        return anz;
    }

    public int anzUnberechnetJugendliche() {
        int anz = 0;
        for (int i = 0; i < personen.size(); i++) {
            if (personen.get(i).isJugendlicher() && !personen.get(i).isBerechnet())
                anz++;
        }
        return anz;
    }

    public int AnzUnberechneten() {
        return anzUnberechnetErwachsene() + anzUnberechnetJugendliche();
    }

    public int getAnzMaxFamilienkarten() {
        int anzKarten = 0;
        int anzErw = getAnzErwachsene();
        int anzJug = getAnzJugendliche();
        Boolean weitereKarte = true;


        while (weitereKarte) {
            weitereKarte = false;

            if (anzErw >= 2 && anzJug >= 2) {
                anzKarten++;
                anzErw -= 2;
                anzJug -= 2;
                weitereKarte = true;
            } else if (anzErw >= 1 && anzJug >= 3) {
                anzKarten++;
                anzErw -= 1;
                anzJug -= 3;
                weitereKarte = true;
            }
        }
        return anzKarten;
    }
}
