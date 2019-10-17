package pl.pollub.controllers;

import com.thalmic.myo.enums.PoseType;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.collectors.TutorialCollector;
import pl.pollub.factory.modes.ModeFactory;
import pl.pollub.factory.modes.TutorialMode;
import pl.pollub.factory.properties.PropertiesFactory;
import pl.pollub.factory.properties.TutorialProperties;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class TutorialController implements Initializable {
    public AnchorPane paneTutorial;
    public Label doubleTabLabel;
    public Label waveOutLabel;
    public Label waveInLabel;
    public Label fistLabel;
    public Label fingersSpreadLabel;
    private final ModeType modeType = ModeType.TUTORIAL_COLLECTOR;
    private final ModeFactory modeFactory = new ModeFactory(new PropertiesFactory(), new CollectorsFactory());

    private static final String LABEL_HIGHLIGHT = "label-highlight";
    private static final String LABEL_GENERATED = "label-generated";
    private TutorialMode mode = (TutorialMode) modeFactory.createMode(modeType);
    private TutorialProperties properties = (TutorialProperties) mode.getProperties();
    private TutorialCollector dataCollector = (TutorialCollector) mode.getDataCollector();

    public void initialize(URL location, ResourceBundle resources) {

        ObjectProperty<PoseType> wantedPoseType = properties.getWantedPoseType();

        dataCollector.generateRandomPose();
        highlightPose(wantedPoseType.getValue(), "label-generated");

        properties.getPoseType().addListener((observable, oldValue, newValue) -> {
            if (newValue == wantedPoseType.getValue()) {
                unlightPose(newValue, LABEL_HIGHLIGHT);
                dataCollector.generateRandomPose();
                highlightPose(wantedPoseType.getValue(), LABEL_GENERATED);
            }
            unlightPose(oldValue, LABEL_HIGHLIGHT);
            highlightPose(newValue, LABEL_HIGHLIGHT);
        });
    }

    private void highlightPose(PoseType poseType, String classStyleName) {
        Platform.runLater(() -> {
            switch (poseType) {
                case WAVE_IN:
                    this.waveInLabel.getStyleClass().add(classStyleName);
                    break;
                case FINGERS_SPREAD:
                    this.fingersSpreadLabel.getStyleClass().add(classStyleName);
                    break;
                case FIST:
                    this.fistLabel.getStyleClass().add(classStyleName);
                    break;
                case DOUBLE_TAP:
                    this.doubleTabLabel.getStyleClass().add(classStyleName);
                    break;
                case WAVE_OUT:
                    this.waveOutLabel.getStyleClass().add(classStyleName);
                    break;
            }
        });
    }


    private void unlightPose(PoseType poseType, String classStyleName) {
        Platform.runLater(() -> {
            switch (poseType) {
                case WAVE_IN:
                    this.waveInLabel.getStyleClass().remove(classStyleName);
                    break;
                case FINGERS_SPREAD:
                    this.fingersSpreadLabel.getStyleClass().remove(classStyleName);
                    break;
                case FIST:
                    this.fistLabel.getStyleClass().remove(classStyleName);
                    break;
                case DOUBLE_TAP:
                    this.doubleTabLabel.getStyleClass().remove(classStyleName);
                    break;
                case WAVE_OUT:
                    this.waveOutLabel.getStyleClass().remove(classStyleName);
                    break;
            }
        });
    }

    public void startMode() {
        mode.startMode();
    }

    public void stopMode() {
        mode.stopMode();
    }
}
