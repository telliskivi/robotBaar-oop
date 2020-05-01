package sample;

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
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    ComboBox joogid;
    @FXML
    TextField kogus;
    @FXML
    CheckBox püsiklient;
    @FXML
    TextField screen;
    @FXML
    Button button;

    ObservableList<String> menuu = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            screen.setText("Tere tulemast!");
            võtaAndmed();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void võtaAndmed() {
        menuu.remove(menuu);
        String a = "Saku Pilsner";
        String b = "Saku Manchester";
        String c = "Saku Mõdu";
        String d = "Saku Hele";
        String e = "Saku Porter";
        menuu.addAll(a,b,c,d,e);
        joogid.getItems().addAll(menuu);
    }

    //Nupuga seotud funktsioonid.

    public void Ostmine(ActionEvent actionEvent) {


    }
    // Kui vajutatakse nuppu, siis kinnitatakse ost.
    public void nupuVajutus(MouseEvent mouseEvent) {
        screen.setText("Ostsid endale joogi!");
    }

    //Kui kursori nupu peale libistad, siis kontrollib, kas on lahtrid valitud.
    public void hiirPeal(MouseEvent mouseEvent) {
        boolean menüüonTühi = (joogid.getValue() == null);
        boolean kogusonTühi = (kogus.getText().isEmpty());

        if (menüüonTühi && kogusonTühi) {
            screen.setText("Vali jook ja kogus.");

        } else if (!menüüonTühi && kogusonTühi){
            screen.setText("Vali kogus.");

        } else if (menüüonTühi && !kogusonTühi){
            screen.setText("Vali jook.");

        } else {

            double hind = Math.round(3 * 1.1);
            String hindS = String.valueOf(hind);
            screen.setText("Valisid " + kogus.getText() + " " + joogid.getValue() + " | Hind: " + hindS + "€");

        }
    }

    //Küsib, kas on püsiklient.
    public void kasonKlient(MouseEvent mouseEvent) {
        screen.setText("Kas oled püsiklient?");
    }

    //Get ja Set meetodid.

    public ComboBox getJoogid() {
        return joogid;
    }

    public void setJoogid(ComboBox joogid) {
        this.joogid = joogid;
    }

    public TextField getKogus() {
        return kogus;
    }

    public void setKogus(TextField kogus) {
        this.kogus = kogus;
    }

    public CheckBox getPüsiklient() {
        return püsiklient;
    }

    public void setPüsiklient(CheckBox püsiklient) {
        this.püsiklient = püsiklient;
    }

    public TextField getScreen() {
        return screen;
    }

    public void setScreen(TextField screen) {
        this.screen = screen;
    }

    public Button getButton() {
        return button;
    }

    public void setButton(Button button) {
        this.button = button;
    }
}



