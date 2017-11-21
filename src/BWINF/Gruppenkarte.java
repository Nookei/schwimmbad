package BWINF;

/**
 * Created by 05021712 on 14.11.2017.
 */
public class Gruppenkarte extends Karte {
    private Person[] inhaber = new Person[4];

    Gruppenkarte(){
        super(1100);
    }

    public boolean eintragen(Person p) {
        Boolean ok = false;

        if (inhaber.length < 4) {
            inhaber[inhaber.length - 1] = p;
            ok = true;
            p.berechnen();
        }

        return ok;
    }
}