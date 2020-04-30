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

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    TextField kogus;
    @FXML
    ComboBox joogid;
    @FXML
    CheckBox püsiklient;
    @FXML
    Button button;
    @FXML
    TextField screen;

    ObservableList<String> menuu = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadData();
    }

    public void võtaKogus(ActionEvent actionEvent) {
    }

    public void nupuVajutus(ActionEvent actionEvent) {
        String ost = (String) joogid.getValue();
        if (ost == null) {
            screen.setText("Vali enda jook.");
        } else if (ost != null && kogus.getText() == null){
            screen.setText("Vali kogus.");
        } else if (ost != null && kogus.getText() != null){
            screen.setText("Valisid " + kogus.getText() + " " + ost + ".\n Oled kindel?");
        }
    }

    @FXML
    private void kuvaOst(ActionEvent event) {
    }

    private void ostuKinnitus() {

    }

    private void loadData() {
        menuu.remove(menuu);
        String a = "Saku Pilsner";
        String b = "Saku Manchester";
        String c = "Saku Mõdu";
        String d = "Saku Hele";
        String e = "Saku Porter";
        menuu.addAll(a,b,c,d,e);
        joogid.getItems().addAll(menuu);
    }

    public TextField getKogus() {
        return kogus;
    }

    public void setKogus(TextField kogus) {
        this.kogus = kogus;
    }

    public ComboBox getJoogid() {
        return joogid;
    }

    public void setJoogid(ComboBox joogid) {
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