package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.collectors.MouseCollector;
import pl.pollub.factory.modes.ModeFactory;
import pl.pollub.factory.modes.MouseMode;
import pl.pollub.factory.properties.MouseProperties;
import pl.pollub.factory.properties.PropertiesFactory;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class MouseController implements Initializable {
    @FXML
    private AnchorPane mouseView;
    @FXML
    private TextField currentPosition;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private final ModeType modeType = ModeType.MOUSE_COLLECTOR;
    private final ModeFactory modeFactory = new ModeFactory(new PropertiesFactory(), new CollectorsFactory());
    private MouseMode mode = (MouseMode) modeFactory.createMode(modeType);
    private MouseProperties properties = (MouseProperties) mode.getProperties();
    private MouseCollector dataCollector = (MouseCollector) mode.getDataCollector();

    public void initialize(URL location, ResourceBundle resources) {
        this.currentPosition.textProperty().bind(properties.getCurrentLocation());

        dataCollector.getCurrentPosition().start();
    }

    public void startMode(){
        mode.startMode();
    }

    public void stopMode() {
        mode.stopMode();
    }
}
