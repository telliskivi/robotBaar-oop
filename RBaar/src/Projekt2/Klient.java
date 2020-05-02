package Projekt2;

import java.io.IOException;

public class Klient implements Kliendid {
    public double õllehind;
    public double rahakogus;

    public Klient(double rahakogus, double õllehind) throws IOException {
        this.rahakogus = rahakogus-maksumus();
        this.õllehind = õllehind;
    }


    public double getRahakogus() {
        return rahakogus;
    }

    public double getÕllehind() {
        return õllehind;
    }


    @Override
    public double maksumus() throws IOException {
        return õllehind;
    }

    public void setÕllehind(double õllehind) {
        this.õllehind = õllehind;
    }

    public void setRahakogus(double rahakogus) {
        this.rahakogus = rahakogus;
    }

    @Override
    public String tervitus() {
        return "Teretulemast, lambiklient!";
    }

    @Override
    public double krediit() throws IOException {
        return rahakogus - maksumus();
    }

    @Override
    public String toString() {
        try {
            return "Teie toote maksumus on " + maksumus() + " eurot" + ",\n" +
                    " teie krediit on " + krediit() + " eurot.";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Midagi on valesti";
    }
}
