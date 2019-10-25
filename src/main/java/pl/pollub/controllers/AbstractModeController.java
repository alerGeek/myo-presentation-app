package pl.pollub.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public interface AbstractModeController extends Initializable {
    public void initialize(URL location, ResourceBundle resources);

    public void startMode();

    public void stopMode();

}
