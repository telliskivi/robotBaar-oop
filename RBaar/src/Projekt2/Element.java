/*package Projekt2;

public class Element<T> {
    public T jou;

    public Element(T jou) {

        this.jou = jou;
    }

    public T getJou() {
        return jou;
    }

    @Override
    public String toString() {
        if(jou instanceof Klient)
        return "Teie toote maksumus on " + Klient.getÕllehind() + " eurot"+
                ", teie krediit on " + Klient.krediit()+" eurot.";

        if (jou instanceof Kuldklient)
            return "Teie toote maksumus"+" on " + Kuldklient.getÕllehind()*0.9 + " eurot, härra "+Kuldklient.getNimi()+
                        ", teie krediit on " + Kuldklient.krediit()+" eurot.";
        return null;
    }
}*/
