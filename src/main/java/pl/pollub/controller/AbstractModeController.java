package pl.pollub.controller;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public interface AbstractModeController extends Initializable {
    void initialize(URL location, ResourceBundle resources);

    void startMode();

    void stopMode();

}
