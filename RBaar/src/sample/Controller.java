package sample;

import Projekt2.JoogiNimed;
import Projekt2.Joogihinnad;
import Projekt2.Klient;
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
import java.util.*;

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



    ObservableList<String> menuu = FXCollections.observableArrayList();


    @Override
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
    public Double hiirPeal(MouseEvent mouseEvent) throws IOException {
        List<Double>a = new ArrayList<>();
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

            } else {
                for (int i = 0; i < joogid.getItems().size(); i++) {
                    if (ost.equals(joogid.getItems().get(i))) {
                        a = hinnad.joogid("Õlled.txt");
                        for (int j = 0; j < a.size(); j++) {
                            a.set(j, (Double.parseDouble(String.valueOf(a.get(j))) * Integer.parseInt(kogus.getText())));

                        }
                        //screen.setText(a.get());
                        õllehind = a.set(i, (Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText())));
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
                        a = hinnad.joogid("Õlled.txt");
                        for (int j = 0; j < a.size(); j++) {
                            String b = String.valueOf(a.set(j, 0.9 * (Double.parseDouble(String.valueOf(a.get(j))) * Integer.parseInt(kogus.getText()))));

                        }
                        //screen.setText(a.get());
                        õllehind = a.set(i, (Double.parseDouble(String.valueOf(a.get(i))) * Integer.parseInt(kogus.getText())));
                        screen.setText(Double.toString(õllehind) + " eurot.");



                    }
                }
            }
        }return õllehind;
    }
    //Küsib, kas on püsiklient.
    public void kasonKlient(MouseEvent mouseEvent) {
        screen.setText("Kas oled püsiklient?");
    }
    // Kui vajutatakse nuppu, siis kinnitatakse ost.
    public void nupuVajutus(MouseEvent mouseEvent) throws IOException {
        screen.setText("Ostsid endale joogi!");
        Klient Jaanus = new Klient(40,õllehind);
        System.out.println(Jaanus.rahakogus);
        System.out.println(Jaanus.krediit());
        System.out.println("Maksumus"+Jaanus.maksumus());
    }

    @FXML
    private void kuvaOst(ActionEvent event) {
    }





    private void võtaAndmed() throws IOException {

        menuu.removeAll();
                try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("Õlled.txt"), StandardCharsets.UTF_8))) {
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
