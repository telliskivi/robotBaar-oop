package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//võtab failist joogihinnad

public class Joogihinnad{

    List<String>a = new ArrayList<>();
    List<String> nimetused = new ArrayList<String>();
    List<Double> hinnad = new ArrayList<>();

    public List<Double> joogid(String failinimi) throws IOException {
        try (BufferedReader loeFailist = new BufferedReader(new FileReader(failinimi));) {
            String rida = loeFailist.readLine();

            while (rida != null) {
                String[]d = rida.split(";");
                hinnad.add(Double.parseDouble(d[1]));
                rida = loeFailist.readLine();
            }
        }
        return hinnad;
    }
}
