package pl.pollub.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.collectors.RobotCollector;
import pl.pollub.factory.modes.ModeFactory;
import pl.pollub.factory.modes.RobotMode;
import pl.pollub.factory.properties.PropertiesFactory;
import pl.pollub.factory.properties.RobotProperties;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.net.URL;
import java.util.ResourceBundle;

public class RobotController implements Initializable {
    @FXML
    private AnchorPane mouseView;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private final ModeType modeType = ModeType.ROBOT_COLLECTOR;
    private final ModeFactory modeFactory = new ModeFactory(new PropertiesFactory(), new CollectorsFactory());
    private RobotMode mode = (RobotMode) modeFactory.createMode(modeType);
    private RobotProperties properties = (RobotProperties) mode.getProperties();
    private RobotCollector dataCollector = (RobotCollector) mode.getDataCollector();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void startMode(){
        mode.startMode();
    }

    public void stopMode(){
        mode.stopMode();
    }

}
