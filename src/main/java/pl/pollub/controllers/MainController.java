package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.devicepropertieslisteners.CommunicateListener;
import pl.pollub.model.devicepropertieslisteners.HubListener;
import pl.pollub.model.devicepropertieslisteners.MyoListener;
import pl.pollub.model.devicepropertieslisteners.MyoStateListener;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;
import pl.pollub.utils.NodesFinder;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

@Getter
@Setter
public class MainController implements Initializable {

    @FXML private AnchorPane mainView;
    @FXML private VBox detailsView;
    @FXML private AnchorPane connectorView;
    @FXML private AnchorPane modesView;
    @FXML private AnchorPane changeModesView;

    @FXML private DetailsController detailsViewController;
    @FXML private ModesController modesViewController;
    @FXML private ChangeModesController changeModesViewController;
    @FXML private ConnectorController connectorViewController;

    private final DeviceFacade facade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private ArrayList<ToggleButton> toggleButtons;
    private ArrayList<ToggleButton> listenerButtons;

    public void initialize(URL location, ResourceBundle resources) {
        this.toggleButtons = NodesFinder.getAllToggleButtons(NodesFinder.getAllNodes(mainView));
        this.listenerButtons = NodesFinder.getListenersButtons(toggleButtons);

        addHubListener();
        addMyoListener();
        addConnectStateListener();
        addCommunicateListener();

        connectorViewController.setMainViewController(this);
        changeModesViewController.setMainViewController(this);
        changeModesViewController.setModesViewController(modesViewController);
        changeModesViewController.setDetailsViewController(detailsViewController);
        changeModesViewController.setRobotViewController(modesViewController.getRobotViewController());
        changeModesViewController.setTutorialViewController(modesViewController.getTutorialViewController());
        changeModesViewController.setMouseViewController(modesViewController.getMouseViewController());
        changeModesViewController.setCommunicateViewController(modesViewController.getCommunicateViewController());
    }


    private void addHubListener() {
        facade.getDevice().getHub().addListener(new HubListener(
                changeModesViewController.getToggleButtons()));
    }

    private void addMyoListener() {
        facade.getDevice().getMyo().addListener(new MyoListener(
                changeModesViewController.getToggleButtons(),
                changeModesViewController.getListenerButtons(),
                connectorViewController.getAddMyoButton()));
    }

    private void addConnectStateListener() {
        facade.getDevice().getMyoState().addListener(new MyoStateListener(
                facade.getDevice().getCommunicate(),
                changeModesViewController.getToggleButtons(),
                changeModesViewController.getListenerButtons(),
                connectorViewController.getAddMyoButton()));
    }

    private void addCommunicateListener() {
        facade.getDevice().getCommunicate().addListener(new CommunicateListener(modesViewController
                .getCommunicateViewController()
                .getCommunicateArea()));
    }
}
