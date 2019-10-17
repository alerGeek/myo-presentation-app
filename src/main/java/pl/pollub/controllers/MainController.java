package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.model.listener.propertieslisteners.HubListener;
import pl.pollub.model.listener.propertieslisteners.MyoListener;
import pl.pollub.model.listener.propertieslisteners.MyoStateListener;
import pl.pollub.tool.FacadeWrapperSingleton;
import pl.pollub.utils.NodesFinder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML
    private AnchorPane main;
    @FXML
    private VBox detailsView;
    @FXML
    private AnchorPane connectorView;
    @FXML
    private AnchorPane modesView;
    @FXML
    private AnchorPane changeModesView;

    @FXML
    private DetailsController detailsViewController;
    @FXML
    private ModesController modesViewController;
    @FXML
    private ChangeModesController changeModesViewController;
    @FXML
    private ConnectorController connectorViewController;

    private final DeviceFacade facade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private ArrayList<ToggleButton> toggleButtons;
    private ArrayList<ToggleButton> listenerButtons;


    public void initialize(URL location, ResourceBundle resources) {
        this.toggleButtons = NodesFinder.getAllToggleButtons(NodesFinder.getAllNodes(main));
        this.listenerButtons = NodesFinder.getListenersButtons(toggleButtons);

        addHubListener();
        addMyoListener();
        addConnectStateListener();

        connectorViewController.setMainController(this);
        changeModesViewController.setMainController(this);
        changeModesViewController.setDetailsController(detailsViewController);
        changeModesViewController.setRobotController(modesViewController.getRobotViewController());
        changeModesViewController.setTutorialController(modesViewController.getTutorialViewController());
        changeModesViewController.setMouseController(modesViewController.getMouseViewController());
        changeModesViewController.setCommunicateController(modesViewController.getCommunicateViewController());
    }


    private void addHubListener() {
        facade.getDevice().getHub().addListener(new HubListener(changeModesViewController.getToggleButtons(), changeModesViewController.getListenerButtons(), connectorViewController.getAddMyoButton()));
    }


    private void addMyoListener() {
        facade.getDevice().getMyo().addListener(new MyoListener(changeModesViewController.getToggleButtons(), changeModesViewController.getListenerButtons(), connectorViewController.getAddMyoButton()));
    }


    private void addConnectStateListener() {
        facade.getDevice().getMyoState().addListener(new MyoStateListener(changeModesViewController.getToggleButtons(), changeModesViewController.getListenerButtons(), connectorViewController.getAddMyoButton()));
    }

}
