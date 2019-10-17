package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class ModesController implements Initializable {
    @FXML
    private AnchorPane robotView;
    @FXML
    private AnchorPane modesView;
    @FXML
    private AnchorPane communicateView;
    @FXML
    private AnchorPane tutorialView;
    @FXML
    private AnchorPane mouseView;
    @FXML
    private AnchorPane  robotPane;


    @FXML
    private RobotController robotViewController;
    @FXML
    private CommunicateController communicateViewController;
    @FXML
    private TutorialController tutorialViewController;
    @FXML
    private MouseController mouseViewController;

    public void initialize(URL location, ResourceBundle resources) {}

}
