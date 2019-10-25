package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

@Getter
@Setter
public class ConnectorController {

    @FXML private AnchorPane connectView;
    @FXML private ToggleButton addMyoButton;

    @FXML private MainController mainViewController;
    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();

    public void addMyoButtonOnAction() {
        deviceFacade.connectMyo();

        mainViewController.getChangeModesViewController().initializeDetailsController();

        deviceFacade.getConnector().getFindMyoTask().setOnSucceeded(
                event -> {
                    if (deviceFacade.getDevice().getHub().get() != null && deviceFacade.getDevice().getMyo().get() != null) {
                        deviceFacade.collectData();
                    }
                }
        );
    }
}
