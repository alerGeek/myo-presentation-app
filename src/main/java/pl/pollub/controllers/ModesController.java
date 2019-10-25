package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class ModesController implements Initializable {
    @FXML private Tab communicateTab;
    @FXML private Tab tutorialTab;
    @FXML private Tab mouseTab;
    @FXML private Tab robotTab;
    @FXML private TabPane modePaneView;
    @FXML private AnchorPane robotView;
    @FXML private AnchorPane modesView;
    @FXML private AnchorPane communicateView;
    @FXML private AnchorPane tutorialView;
    @FXML private AnchorPane mouseView;
    @FXML private AnchorPane robotPane;

    @FXML private RobotController robotViewController;
    @FXML private CommunicateController communicateViewController;
    @FXML private TutorialController tutorialViewController;
    @FXML private MouseController mouseViewController;

    public void initialize(URL location, ResourceBundle resources) {
    }

    public void selectTab(ModeType modeType) {
        switch (modeType) {
            case MOUSE_COLLECTOR:
                getModePaneView().getSelectionModel().select(mouseTab);
                break;
            case COMMUNICATE_COLLECTOR:
                getModePaneView().getSelectionModel().select(communicateTab);
                break;
            case TUTORIAL_COLLECTOR:
                getModePaneView().getSelectionModel().select(tutorialTab);
                break;
            case ROBOT_COLLECTOR:
                getModePaneView().getSelectionModel().select(robotTab);
                break;
            default:
                return;
        }
    }
}
