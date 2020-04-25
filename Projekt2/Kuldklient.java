package Projekt2;

import java.util.Scanner;

public class Kuldklient extends Klient implements Kliendid{
    String nimi;
    public Kuldklient(double rahakogus, double õllehind, String nimi) {
        super(rahakogus, õllehind);
        this.nimi = nimi;

    }

    @Override
    public double maksumus() {
        return õllehind*0.9;
    }


    @Override
    public String tervitus() {
        return "TERE-TERE, "+nimi+"!";
    }

    @Override
    public String toString() {
        return "Teie toote maksumus on " + maksumus() + " eurot, härra "+nimi+",\n"+
                " teie krediit on " + krediit()+" eurot.";
    }
}
