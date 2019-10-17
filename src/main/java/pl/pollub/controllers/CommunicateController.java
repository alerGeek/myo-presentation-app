package pl.pollub.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.collectors.CommunicateCollector;
import pl.pollub.factory.modes.CommunicateMode;
import pl.pollub.factory.modes.ModeFactory;
import pl.pollub.factory.properties.CommunicateProperties;
import pl.pollub.factory.properties.PropertiesFactory;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class CommunicateController implements Initializable {
    @FXML
    public TextArea communicateArea;
    public AnchorPane paneApp;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
    private final ModeType modeType = ModeType.COMMUNICATE_COLLECTOR;
    private final ModeFactory modeFactory = new ModeFactory(new PropertiesFactory(), new CollectorsFactory());
    private CommunicateMode mode = (CommunicateMode) modeFactory.createMode(modeType);
    private CommunicateProperties properties = (CommunicateProperties) mode.getProperties();
    private CommunicateCollector dataCollector = (CommunicateCollector) mode.getDataCollector();


    public void initialize(URL location, ResourceBundle resources) {
//        FacadeWrapperSingleton.INSTANCE.getDevice().getCommunicate().bindBidirectional(properties.getAllCommunicate());
//        properties.getNewCommunicate().bindBidirectional(FacadeWrapperSingleton.INSTANCE.getDevice().getCommunicate());
//        communicateArea.textProperty().bindBidirectional(properties.getAllCommunicate());
//        startMode();
    }

    public void startMode() {
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
        deviceFacade.getDevice().getCommunicate()
                .addListener((observable, oldValue, newValue) ->
                        Platform.runLater(() -> {
                            if (!(oldValue == null) && !(oldValue.equals(""))) {
                                communicateArea.appendText("\n");
                            }
                            communicateArea.appendText(newValue);
                        })
                );


    }

    public void exitMode() {

    }
}
