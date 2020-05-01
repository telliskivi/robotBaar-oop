/*package Projekt2;

import sample.Controller;

import java.io.IOException;

public class Kuldklient implements Kliendid{
    String nimi;
    public Kuldklient(double rahakogus, double õllehind, String nimi) {
        super(õllehind);
        this.nimi = nimi;

    }

    @Override
    public double maksumus() {
        return õllehind*0.9;
    }

    @Override
    public double krediit() throws IOException {
        return 0;
    }


    @Override
    public String tervitus() {
        return "TERE-TERE, "+nimi+"!";
    }

    @Override
    public String toString() {
        try {
            return "Teie toote maksumus on " + maksumus() + " eurot, härra "+nimi+",\n"+
                    " teie krediit on " + krediit()+" eurot.";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Midagi on halvasti";
    }
}*/
