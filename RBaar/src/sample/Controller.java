package sample;

import Projekt2.Joogihinnad;
import Projekt2.SisestusErind;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    int[] numbrid = IntStream.range(1, 100).toArray();
    ObservableList<String> menuu = FXCollections.observableArrayList();


    @Override //Meetod, mis käivitub programmi avamisel ning nagu näha, võtab andmed.
    public void initialize(URL url, ResourceBundle rb) {
        try {
            võtaAndmed();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void võtaKogus(ActionEvent actionEvent) {
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
                        screen.setText(Double.toString(õllehind) + " eurot.");
                    }
                }
            }
        } else {
            if (ost.equals("")) {
                screen.setText("Vali endale jook!");
            } else if (!ost.equals("") && kogus.getText().equals("")) {
                screen.setText("Vali kogus!");
            } else if (!ost.equals("") && !(kogus.getText().equals(""))) {
                for (int i = 0; i < joogid.getItems().size(); i++) {
                    if (ost.contentEquals((CharSequence) joogid.getItems().get(i))) {
                        a = hinnad.joogid("Joogid.txt");
                        for (int j = 0; j < a.size(); j++) {
                            String b = String.valueOf(a.set(j, 0.9 * (Double.parseDouble(String.valueOf(a.get(j))) * Integer.parseInt(kogus.getText()))));

                        }
                        õllehind = a.set(i, 0.9*(Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText())));
                        screen.setText(Double.toString(õllehind) + " eurot.");


                    }
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
//Siin teeme veidi lausearvutust
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
            screen.setText("Täname ostu eest! Teie kulutused on " + Double.toString(kogutulu()) + " eurot.");

        }
    }

    public static boolean onTäisarv(String s, int radix) { //meetod aitab kontrollida, kas tegu on täisarvuga või mitte.
        if (s.isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (i == 0 && s.charAt(i) == '-') {
                if (s.length() == 1) return false;
                else continue;
            }
            if (Character.digit(s.charAt(i), radix) < 0) return false;
        }
        return true;
    }



    private void võtaAndmed() throws IOException {
//Loeme tekstifailist joogid.txt elemendid ning lisame nende info ComboBoxi.
        menuu.removeAll();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Joogid.txt"), StandardCharsets.UTF_8))) {
            String rida1 = br.readLine();
            while (rida1 != null) {
                String[] a = rida1.split(";");
                double hind1 = Double.parseDouble(a[1]);
                System.out.println(rida1);
                rida1 = br.readLine();
                menuu.addAll(a[0] + " - " + Double.parseDouble(a[1]) + "€");
            }

        }
        joogid.getItems().addAll(menuu); //lisame menüü pardale


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

    private void kirjutaFaili() throws IOException { //Kirjutame jookide info faili "Ostud.txt"
        try {
            Files.write(Paths.get("Ostud.txt"), (kogus.getText() + ";" + joogid.getValue() + ";" + Double.toString(õllehind) + "\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {


        }
    } //Meetod loeb failist "Ostud.txt" ning väljastab sealse info põhjal ostude kogusumma.
    private double kogutulu() throws Exception{
        double summa = 0;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Ostud.txt"), StandardCharsets.UTF_8))) {
            String rida1 = br.readLine();
            rida1 = br.readLine();
            while (rida1 != null) {
                String[] a = rida1.split(";");
                double hind1 = Double.parseDouble(a[2]);
                summa += hind1;
                System.out.println(summa);
                rida1 = br.readLine();



            }
        }return summa;
    }

}

