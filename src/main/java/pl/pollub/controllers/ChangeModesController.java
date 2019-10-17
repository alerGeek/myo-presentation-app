package pl.pollub.controllers;

import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;
import pl.pollub.utils.NodesFinder;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
public class ChangeModesController implements Initializable {
    public AnchorPane buttonsPane;

    public ToggleButton listenerPresentationButton;
    public ToggleButton listenerTutorialButton;
    public ToggleButton listenerMouseButton;

    private List<ToggleButton> toggleButtons;
    private List<ToggleButton> listenerButtons;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private MainController mainController;
    private RobotController robotController;
    private DetailsController detailsController;
    private MouseController mouseController;
    private TutorialController tutorialController;
    private CommunicateController communicateController;

    public void initialize(URL location, ResourceBundle resources) {
        this.toggleButtons = NodesFinder.getAllToggleButtons(NodesFinder.getAllNodes(this.buttonsPane));
        this.listenerButtons = NodesFinder.getListenersButtons(this.toggleButtons);
    }

    public void initialiseDetailsController() {
        if(detailsController.getMode().getProperties().getIsActive().get()){
            return;
        }
        detailsController.startMode();
    }

    public void initialiseCommunicateController() {
        if(communicateController.getMode().getProperties().getIsActive().get()){
            return;
        }

        communicateController.startMode();
    }


    public void presentationButtonOnAction() {
        if (!listenerPresentationButton.isSelected()) {
            robotController.stopMode();
        } else {
            robotController.startMode();
        }
    }

    public void tutorialButtonOnAction() {
        if (!listenerTutorialButton.isSelected()) {
            tutorialController.stopMode();
        } else {
            tutorialController.startMode();
        }
    }

    public void mouseButtonOnAction() {
        if (!listenerMouseButton.isSelected()) {
            mouseController.stopMode();
        } else {
            mouseController.startMode();
        }
    }
}
