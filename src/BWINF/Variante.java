package BWINF;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by 05021712 on 14.11.2017.
 */
public class Variante {
    Buchung b;
    ArrayList<Karte> auswahl = new ArrayList<>();

    Variante(Buchung b) {
        this.b = b;

    }

    public void berechnen(int gruppenkarten, int familienkarten) {

        Gruppenkarte gk;
        Familienkarte fk;
        EinzelkarteErw ekw;
        EinzelkarteJug ekj;
        Person p;

        // Berechnung Familienkarten
        for (int i = 0; i < familienkarten; i++) { // Alle in der Variante zu erstellende Familienkarten generieren
            fk = new Familienkarte();
            int anzErwachsener = 0;

            for (int j = 0; j < 4; j++) { // 4 Personen eintragen
                if (b.hasErwachsenen() && anzErwachsener < 3) {
                    fk.eintragen(b.getErwachsenen());
                    anzErwachsener++;
                } else if (b.anzUnberechnetJugendliche() > 0){
                    fk.eintragen(b.getJugendlichen());
                }
            }
            eintragen(fk);

        }
        if (!b.isInFerien()) {
            //Berechnung Gruppenkarte
            for (int i = 0; i < gruppenkarten; i++) {
                gk = new Gruppenkarte();
                for (int j = 0; j <= 6; j++) {
                    p = b.getErwachsenen();
                    if (p != null)
                        gk.eintragen(p);
                    else {
                        p = b.getJugendlichen();
                        gk.eintragen(p);
                    }
                }
                eintragen(gk);
            }
        }


        //Berechnung Einzelkarten
        for (int i = 0; i < b.AnzUnberechneten(); i++) {
            p = b.getUnberechneten();
            if (p.isErwachsener()) {
                ekw = new EinzelkarteErw(p);
                eintragen(ekw);
            } else {
                ekj = new EinzelkarteJug(p);
                eintragen(ekj);
            }
        }

        rabattBerechnen();
    }

    private void eintragen(Karte k) {
        auswahl.add(k);
    }

    public int getPreis() {
        int preisAsInt = 0;

        for (Karte k : auswahl) {
            preisAsInt += k.getPreis();
        }

        return preisAsInt;
    }

    private String toEuroAsString(int betrag) {
        //Gibt Beträge vom Typ Int als double mit 2 Nachkommastellen zurück
        DecimalFormat f = new DecimalFormat("#0.00");

        double back = betrag;
        back /= 100;
        return f.format(back);
    }

    private void rabattBerechnen() {
        Boolean prozentRabattUsed = false;
        Boolean einzelErwFound = false;
        Person p = new Person(false, true);
        EinzelkarteErw erw = new EinzelkarteErw(p);
        EinzelkarteJug erj = new EinzelkarteJug(p);


        // Rabattieren von Einzelkarten wenn Buchung unter der Woche
        if (b.isWochentags()) {
            for (Karte k : auswahl) {
                if (k.getClass().isInstance(erw) || k.getClass().isInstance(erj))
                    k.rabattieren(20);
            }
        }

        // Rabattieren durch Gutscheine
        if (!b.isInFerien() && b.getAnzGutscheine() > 0) {
            for (int i = 0; i < b.getAnzGutscheine(); i++) {
                if (!prozentRabattUsed && getErsparnisFreikarte() < getErsparnisProzentrabatt()) { // Was gibt mehr Ersparnis ? Freikarte oder 10% Rabatt

                    for (Karte k : auswahl) { // Alle Karten 10 % Rabattieren
                        k.rabattieren(10);
                    }
                    prozentRabattUsed = true;

                } else { //Freikarten

                    for (Karte k : auswahl) { // Ist Einzelkarte Erwachsener vorhanden
                        einzelErwFound = false;
                        if (k.getClass().isInstance(erw) && k.getPreis() != 0) {
                            k.rabattieren(100);
                            einzelErwFound = true;
                            break;
                        }
                    }

                    if (!einzelErwFound) { // Wenn Einzelkarte Erwachsener nicht vorhanden dann Einzelkarte Jugendlicher
                        for (Karte k : auswahl) {
                            if (k.getClass().isInstance(erj) && k.getPreis() != 0) {
                                k.rabattieren(100);
                                break;
                            }
                        }
                    }

                }
            } // for Schleife über anzahl Gutscheine
        }
    }

    private int getErsparnisProzentrabatt() {
        int ersparnis = 0;

        for (Karte k : auswahl) {
            ersparnis += (k.getPreis()) * 10;
        }

        return ersparnis / 100;
    }

    private int getErsparnisFreikarte() {
        int ersparnis = 0;

        // Überprüfen ob Erwachsenenkarte vorhanden
        for (Karte k : auswahl) {
            if (k.getClass().isInstance(EinzelkarteErw.class))
                ersparnis = 350;
        }

        //Überprüfen ob Jugendlichenkarte vorhanden
        for (Karte k : auswahl) {
            if (k.getClass().isInstance(EinzelkarteJug.class))
                ersparnis = 250;
        }

        return ersparnis;
    }

    public ArrayList<Karte> getAuswahl() {
        return this.auswahl;
    }

    @Override
    public String toString() {
        int gesamtpreis = 0;
        String back;

        back = "Buchung:\n";
        back += "Erwachsene : " + b.getAnzErwachsene() + "\n";
        back += "Jugendliche : " + b.getAnzJugendliche() + "\n";

        if (b.isWochentags())
            back += "Unter der Woche\n";
        else
            back += "Am Wochenende\n";

        if (b.isInFerien())
            back += "In den Ferien\n";
        else
            back += "Ausserhalb der Ferien\n";

        back += "Mit " + b.getAnzGutscheine() + " Gutscheinen\n";

        for (Karte k : auswahl) {
            if (k instanceof EinzelkarteJug)
                back += "Einzelkarte Jugendlicher\t\tá " + toEuroAsString(k.getPreis()) + " € = " + toEuroAsString(gesamtpreis += k.getPreis()) + " €\n";
            else if (k instanceof EinzelkarteErw)
                back += "Einzelkarte Erwachsener\t\t\tá " + toEuroAsString(k.getPreis()) + " € = " + toEuroAsString(gesamtpreis += k.getPreis()) + " €\n";
            else if (k instanceof Familienkarte)
                back += "Familienkarte\t\t\t\t\tá " + toEuroAsString(k.getPreis()) + " € = " + toEuroAsString(gesamtpreis += k.getPreis()) + " €\n";
            else if (k instanceof  Gruppenkarte)
                back += "Gruppenkarte\t\t\t\t\tá " + toEuroAsString(k.getPreis()) + " € = " + toEuroAsString(gesamtpreis += k.getPreis()) + " €\n";
        }
        back += "\t\t\t\t\t\t\t\t\t\t\t=====\n\n";

        return back;
    }
}
