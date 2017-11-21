package BWINF;
/**
 * Created by 05021712 on 14.11.2017.
 */
public abstract class Einzelkarte extends Karte {
    private Person inhaber;

    Einzelkarte(Person p,int preis) {
        super(preis);
        this.inhaber = p;
        p.berechnen();
    }
}