package pl.pollub.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.types.ModeType;
import pl.pollub.tool.NodesFinder;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Getter
@Setter
public class ChangeModesController implements Initializable {
    @FXML private AnchorPane buttonsPane;
    @FXML private ToggleButton listenerPresentationButton;
    @FXML private ToggleButton listenerTutorialButton;
    @FXML private ToggleButton listenerMouseButton;

    private List<ToggleButton> toggleButtons;
    private List<ToggleButton> listenerButtons;

    @FXML private MainController mainViewController;
    @FXML private ModesController modesViewController;
    @FXML private PresentationController presentationViewController;
    @FXML private DetailsController detailsViewController;
    @FXML private MouseController mouseViewController;
    @FXML private TutorialController tutorialViewController;
    @FXML private CommunicateController communicateViewController;

    public void initialize(URL location, ResourceBundle resources) {
        this.toggleButtons = NodesFinder.getAllToggleButtons(NodesFinder.getAllNodes(this.buttonsPane));
        this.listenerButtons = NodesFinder.getListenersButtons(this.toggleButtons);
    }

    public void initializeDetailsController() {
        if (detailsViewController.getMode().getProperties().getIsActive().get()) {
            return;
        }
        detailsViewController.startMode();
        modesViewController.selectTab(ModeType.COMMUNICATE);
    }

    public void presentationHandler() {
        if (!listenerPresentationButton.isSelected()) {
            presentationViewController.stopMode();
        } else {
            presentationViewController.startMode();
            modesViewController.selectTab(ModeType.COMMUNICATE);
        }
    }

    public void tutorialHandler() {
        if (!listenerTutorialButton.isSelected()) {
            tutorialViewController.stopMode();
        } else {
            tutorialViewController.startMode();
            modesViewController.selectTab(ModeType.TUTORIAL);
        }
    }

    public void mouseHandler() {
        if (!listenerMouseButton.isSelected()) {
            mouseViewController.stopMode();
        } else {
            mouseViewController.startMode();
            modesViewController.selectTab(ModeType.MOUSE);
        }
    }
}
