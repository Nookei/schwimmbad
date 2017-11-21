package BWINF;

/**
 * Created by 05021712 on 14.11.2017.
 */
public class Familienkarte extends Karte {
    private Person[] inhaber = new Person[4];
    int anzEinträge = 0;

    Familienkarte() {
        super(800);
    }

    public void eintragen(Person p) {
        if (anzEinträge < 5) {
            inhaber[anzEinträge] = p;
            anzEinträge++;
            p.berechnen();
        }
    }
}