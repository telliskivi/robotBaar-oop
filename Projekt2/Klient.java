package Projekt2;

import Projekt2.Kliendid;

public class Klient implements Kliendid {
    public double õllehind;
    public double rahakogus;
    public Klient(double rahakogus, double õllehind) {
        this.rahakogus = rahakogus;
        this.õllehind = õllehind;
    }


    public double getRahakogus() {
        return rahakogus;
    }

    public double getÕllehind() {
        return õllehind;
    }


    @Override
    public double maksumus() {
        return õllehind;
    }

    @Override
    public String tervitus() {
        return "Teretulemast, lambiklient!";
    }

    @Override
    public double krediit() {
        return rahakogus-maksumus();
    }

    @Override
    public String toString() {
        return "Teie toote maksumus on " + maksumus() + " eurot"+",\n"+
                " teie krediit on " + krediit()+" eurot.";
    }
}
