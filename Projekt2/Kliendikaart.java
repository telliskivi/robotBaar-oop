package Projekt2;

public class Kliendikaart {
    String nimi;
    int rahasumma;

    public Kliendikaart(String nimi, int rahasumma) {
        this.nimi = nimi;
        this.rahasumma = rahasumma;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public int getRahasumma() {
        return rahasumma;
    }

    public void setRahasumma(int rahasumma) {
        this.rahasumma = rahasumma;
    }
}
