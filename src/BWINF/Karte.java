package BWINF;

/**
 * Created by 05021712 on 14.11.2017.
 */
public abstract class Karte {
    private int preis;

    Karte(int preis){
        this.preis = preis;
    }

    public int getPreis(){
        return preis;
    }

    public void rabattieren(int rabatt){
        int h = preis*100;

        h = (h / 100) * rabatt;

        preis -= (h/100);
    }

}