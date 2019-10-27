package pl.pollub.controller;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.device.DeviceFacade;
import pl.pollub.modefactory.ModeFactory;
import pl.pollub.modefactory.mode.detailsmode.DetailsMode;
import pl.pollub.modefactory.mode.detailsmode.DetailsProperties;
import pl.pollub.util.FacadeWrapperSingleton;
import pl.pollub.types.ModeType;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class DetailsController implements AbstractModeController {
    @FXML private VBox myoDetails;
    @FXML private TextField myoStateField;
    @FXML private TextField handField;
    @FXML private TextField poseField;
    @FXML private TextField lockField;
    @FXML private TextField batteryField;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getDeviceFacade();
    private final DetailsMode mode = (DetailsMode) ModeFactory.createMode(ModeType.DETAILS);

    public void initialize(URL location, ResourceBundle resources) {
        DetailsProperties properties = mode.getProperties();

        deviceFacade.getDevice().getMyoState().bindBidirectional(properties.getConnectState());

        this.poseField.textProperty().bind(properties.getPoseType().asString());
        this.myoStateField.textProperty().bind(properties.getConnectState().asString());
        this.handField.textProperty().bind(properties.getWhichArm().asString());
        this.lockField.textProperty().bind(Bindings.createStringBinding(
                () -> properties.getIsLocked().get() ? "LOCKED" : "UNLOCKED"));
        this.batteryField.textProperty().bind(properties.getBatteryLevel().asString());
    }

    public void startMode() {
        deviceFacade.getCollectorService().setOnSucceeded(
                event -> {
                    if (deviceFacade.getDevice().getHub().get() == null || deviceFacade.getDevice().getMyo() == null) {
                        return;
                    }
                    this.mode.startMode();
                }
        );
    }

    @Override
    public void stopMode() {
        return;
    }
}
