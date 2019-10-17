package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.collectors.DetailsCollector;
import pl.pollub.factory.modes.DetailsMode;
import pl.pollub.factory.modes.ModeFactory;
import pl.pollub.factory.properties.DetailsProperties;
import pl.pollub.factory.properties.PropertiesFactory;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class DetailsController implements Initializable {
    @FXML
    private VBox myoDetails;
    @FXML
    private TextField myoStateField;
    @FXML
    private TextField handField;
    @FXML
    private TextField poseField;
    @FXML
    private TextField lockField;
    @FXML
    private TextField batteryField;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private final ModeType modeType = ModeType.DETAILS_COLLECTOR;
    private final ModeFactory modeFactory = new ModeFactory(new PropertiesFactory(), new CollectorsFactory());
    private DetailsMode mode = (DetailsMode) modeFactory.createMode(modeType);
    private DetailsProperties properties = (DetailsProperties) mode.getProperties();
    private DetailsCollector dataCollector = (DetailsCollector) mode.getDataCollector();

    public void initialize(URL location, ResourceBundle resources) {

        properties.getConnectState().bindBidirectional(deviceFacade.getDevice().getMyoState());

        this.poseField.textProperty().bind(properties.getPoseType().asString());
        this.myoStateField.textProperty().bind(properties.getConnectState().asString());
        this.handField.textProperty().bind(properties.getWhichArm().asString());
        this.lockField.textProperty().bind(properties.getIsLocked().asString());
        this.batteryField.textProperty().bind(properties.getBatteryLevel().asString());

    }

    public void startMode(){
        deviceFacade.getCollectorService().setOnSucceeded(
                event -> {
                    if(deviceFacade.getDevice().getHub().get() == null || deviceFacade.getDevice().getMyo() == null){
                        return;
                    }
                    if (!deviceFacade.getDevice().getDataCollectors().contains(dataCollector)) {
                        mode.startMode();
                    }
                }
        );
    }
}
