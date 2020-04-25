package Projekt2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JoogiNimed {
    String failinimi;
    List<String>a = new ArrayList<>();
    List<String> nimetused = new ArrayList<String>();
    List<String> hinnad = new ArrayList<String>();
    public List<String> õlled(String failinimi) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(failinimi));) {
            String rida1 = br.readLine();
            while (rida1 != null) {
                String[]d = rida1.split(";");
                nimetused.add(d[0]);
                rida1 = br.readLine();
            }
        }
        return nimetused;
    }

    public static void main(String[] args) throws IOException {
        JoogiNimed a = new JoogiNimed();
        System.out.println(a.õlled("õlled.txt"));
    }


}