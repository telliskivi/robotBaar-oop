package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.IntStream;

public class Controller implements Initializable {

    @FXML
    TextField kogus;
    @FXML
    ComboBox<String> joogid;
    @FXML
    CheckBox püsiklient;
    @FXML
    Button button;
    @FXML
    TextField screen;
    @FXML

    double õllehind;
    int[] numbrid = IntStream.range(1, 100).toArray(); //array mille abil kontrollitakse, et tekstilahtrisse sisestatud meetod on 1-100 vahel
    ObservableList<String> menuu = FXCollections.observableArrayList(); //list mille abil võetakse failist tooted ning kuvatakse comboBox'i


    @Override //Meetod, mis käivitub programmi avamisel ning nagu näha, võtab andmed.
    public void initialize(URL url, ResourceBundle rb) {
        try {
            screen.setText("Tere tulemast!");
            võtaAndmed();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Loeme tekstifailist joogid.txt elemendid ning lisame nende info ComboBoxi.
    private void võtaAndmed() throws IOException {

        menuu.removeAll();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Joogid.txt"), StandardCharsets.UTF_8))) {
            String rida1 = br.readLine();

            while (rida1 != null) {

                String[] a = rida1.split(";");
                double hind1 = Double.parseDouble(a[1]);
                rida1 = br.readLine();
                menuu.addAll(a[0] + " - " + Double.parseDouble(a[1]) + "€");
            }

        }
        joogid.getItems().addAll(menuu); //lisame menüü pardale
    }

    //Kirjutame jookide info faili "Ostud.txt"
    private void kirjutaFaili() throws IOException {

        try {
            Files.write(Paths.get("Ostud.txt"), (kogus.getText() + ";" + joogid.getValue() + ";" + õllehind + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {


        }
    }

    //Meetod loeb failist "Ostud.txt" ning väljastab sealse info põhjal ostude kogusumma.
    private double kogutulu() throws Exception{

        double summa = 0;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Ostud.txt"), StandardCharsets.UTF_8))) {

            String rida1 = br.readLine();
            rida1 = br.readLine();

            while (rida1 != null) {

                String[] a = rida1.split(";");
                double hind1 = Double.parseDouble(a[2]);
                summa += hind1;
                rida1 = br.readLine();


            }
        }
        return summa;
    }

    //Kui kursori nupu peale libistad, siis kontrollib, kas on lahtrid valitud.
    //Teeb kindlaks, millist toodet kasutaja soovib osta.
    public Double hiirPeal(MouseEvent mouseEvent) throws IOException {

        List<Double> a = new ArrayList<>();
        Joogihinnad hinnad = new Joogihinnad();
        boolean menüüonTühi = (joogid.getValue() == null);
        boolean kogusonTühi = (kogus.getText().isEmpty());
        String ost = String.valueOf(joogid.getValue());

        if (!(getPüsiklient().isSelected())) {

            if (menüüonTühi && kogusonTühi) {
                screen.setText("Vali jook ja kogus.");

            } else if (!menüüonTühi && kogusonTühi) {
                screen.setText("Vali kogus.");

            } else if (menüüonTühi && !kogusonTühi) {
                screen.setText("Vali jook.");

            } else if (!(Arrays.toString(numbrid).contains(kogus.getText()))) {
                screen.setText("Palun vali täisarv 1 ja 100 vahel");

            } else {
                for (int i = 0; i < joogid.getItems().size(); i++) {
                    if (ost.equals(joogid.getItems().get(i))) {
                        a = hinnad.joogid("Joogid.txt");
                        for (int j = 0; j < a.size(); j++) {
                            a.set(j, Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText()));

                        }
                        //screen.setText(a.get());
                        õllehind = a.set(i, Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText()));
                        screen.setText(Double.toString(õllehind) + " €");
                    }
                }
            }
        } else {

                for (int i = 0; i < joogid.getItems().size(); i++) {
                    if (ost.contentEquals(joogid.getItems().get(i))) {
                        a = hinnad.joogid("Joogid.txt");
                        for (int j = 0; j < a.size(); j++) {
                            String b = String.valueOf(a.set(j, 0.9 * (Double.parseDouble(String.valueOf(a.get(j))) * Integer.parseInt(kogus.getText()))));

                        }
                        õllehind = a.set(i, 0.9*(Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText())));
                        screen.setText(õllehind + " €");


                    }
                }
            }
        return õllehind;
    }

    //Küsib, kas on püsiklient.
    public void kasonKlient(MouseEvent mouseEvent) {
        if (!püsiklient.isSelected())
            screen.setText("Kas oled püsiklient?");

    }

    // Kui vajutatakse nuppu, siis kinnitatakse ost, kui vajalikud tingimused on täidetud.
    public void nupuVajutus(MouseEvent mouseEvent) throws Exception {

        boolean menüüonTühi = (joogid.getValue() == null);
        boolean kogusonTühi = (kogus.getText().isEmpty());

        if (menüüonTühi && kogusonTühi) {
            screen.setText("Vali jook ja kogus.");

        } else if (!menüüonTühi && kogusonTühi) {
            screen.setText("Vali kogus.");

        } else if (menüüonTühi && !kogusonTühi) {
            screen.setText("Vali jook.");

        } else if (!(Arrays.toString(numbrid).contains(kogus.getText()))) {
            screen.setText("Palun vali täisarv 1 ja 100 vahel");
        } else {

            //Kui kõik on korras, kirjutame tulemuse faili ning toome
            //nähtavale siiani ostetud jookidest kogutulu

            kirjutaFaili();
            screen.setText("Täname ostu eest! Teie kulutused on " + kogutulu() + " €");

        }
    }




    //get- ja set-meetodid

    public TextField getKogus() {
        return kogus;
    }

    public void setKogus(TextField kogus) {
        this.kogus = kogus;
    }

    public ComboBox<String> getJoogid() {
        return joogid;
    }

    public void setJoogid(ComboBox<String> joogid) {
        this.joogid = joogid;
    }

    public CheckBox getPüsiklient() {
        return püsiklient;
    }

    public void setPüsiklient(CheckBox püsiklient) {
        this.püsiklient = püsiklient;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }

}

