package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Joogihinnad{
    List<String>a = new ArrayList<>();
    List<String> nimetused = new ArrayList<String>();
    List<Double> hinnad = new ArrayList<>();
    public List<Double> joogid(String failinimi) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(failinimi));) {
            String rida1 = br.readLine();
            while (rida1 != null) {
                String[]d = rida1.split(";");
                hinnad.add(Double.parseDouble(d[1]));
                rida1 = br.readLine();
            }
        }
        return hinnad;
    }

}
