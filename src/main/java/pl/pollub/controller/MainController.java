package pl.pollub.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.device.propertylistener.CommunicateListener;
import pl.pollub.device.propertylistener.HubListener;
import pl.pollub.device.propertylistener.MyoListener;
import pl.pollub.device.propertylistener.MyoStateListener;
import pl.pollub.device.DeviceFacade;
import pl.pollub.util.FacadeWrapperSingleton;
import pl.pollub.tool.NodesFinder;

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

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getDeviceFacade();
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
        changeModesViewController.setPresentationViewController(modesViewController.getPresentationViewController());
        changeModesViewController.setTutorialViewController(modesViewController.getTutorialViewController());
        changeModesViewController.setMouseViewController(modesViewController.getMouseViewController());
        changeModesViewController.setCommunicateViewController(modesViewController.getCommunicateViewController());
    }


    private void addHubListener() {
        deviceFacade.getDevice().getHub().addListener(new HubListener(
                changeModesViewController.getToggleButtons()));
    }

    private void addMyoListener() {
        deviceFacade.getDevice().getMyo().addListener(new MyoListener(
                changeModesViewController.getToggleButtons(),
                connectorViewController.getAddMyoButton()));
    }

    private void addConnectStateListener() {
        deviceFacade.getDevice().getMyoState().addListener(new MyoStateListener(
                deviceFacade.getDevice().getCommunicate(),
                changeModesViewController.getToggleButtons(),
                changeModesViewController.getListenerButtons(),
                connectorViewController.getAddMyoButton()));
    }

    private void addCommunicateListener() {
        deviceFacade.getDevice().getCommunicate().addListener(new CommunicateListener(modesViewController
                .getCommunicateViewController()
                .getCommunicateArea()));
    }
}
