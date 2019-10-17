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

    @FXML
    private AnchorPane connectView;
    @FXML
    private ToggleButton addMyoButton;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private MainController mainController;

    public void addMyoButtonOnAction() {
        deviceFacade.connectMyo();

        mainController.getChangeModesViewController().initialiseDetailsController();
        mainController.getChangeModesViewController().initialiseCommunicateController();

        deviceFacade.getConnector().getFindMyoTask().setOnSucceeded(
                event -> {
                    if (deviceFacade.getDevice().getHub().get() != null && deviceFacade.getDevice().getMyo().get() != null) {
                        deviceFacade.collectData();
                    }
                }
        );
    }
}
