package BWINF;

import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * Created by 05021712 on 07.11.2017.
 */
public class Person {
    private Boolean isJugendlicher;
    private Boolean isErwachsener;
    private Boolean hasBegleitperson = true;
    private Boolean isBerechnet = false;

    public Person(Boolean isJugendlicher, Boolean isErwachsener) {
        this.isErwachsener = isErwachsener;
        this.isJugendlicher = isJugendlicher;

        if (!isErwachsener && !isJugendlicher) {
            hasBegleitperson = false;
        }
    }
    public void berechnen(){
        this.isBerechnet = true;
    }
    public void reset(){
        this.isBerechnet = false;
    }
    public Boolean isBerechnet(){
        return this.isBerechnet;
    }
    public Boolean isJugendlicher(){
        return isJugendlicher;
    }

    public Boolean isErwachsener(){
        return isErwachsener;
    }

    public Boolean isKind(){
        Boolean back = false;

        if (!isJugendlicher() && !isErwachsener())
            back = true;

        return back;
    }
}