package Projekt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Joogihinnad{
    List<String>a = new ArrayList<>();
    List<String> nimetused = new ArrayList<String>();
    List<String> hinnad = new ArrayList<String>();
    public List<String> õlled(String failinimi) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(failinimi));) {
            String rida1 = br.readLine();
            while (rida1 != null) {
                String[]d = rida1.split(";");
                hinnad.add(d[1]);
                rida1 = br.readLine();
            }
        }
        return hinnad;
    }

    public static void main(String[] args) throws IOException {
        Joogihinnad k = new Joogihinnad();
        System.out.println(k.õlled("õlled.txt"));
    }
}
