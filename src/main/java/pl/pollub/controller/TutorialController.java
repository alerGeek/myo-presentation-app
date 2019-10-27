package pl.pollub.controller;

import com.thalmic.myo.enums.PoseType;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.modefactory.ModeFactory;
import pl.pollub.types.ModeType;
import pl.pollub.modefactory.mode.tutorialmode.TutorialCollector;
import pl.pollub.modefactory.mode.tutorialmode.TutorialMode;
import pl.pollub.modefactory.mode.tutorialmode.TutorialProperties;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

@Getter
@Setter
public class TutorialController implements AbstractModeController {
    @FXML private AnchorPane paneTutorial;
    @FXML private Label doubleTap;
    @FXML private Label waveOut;
    @FXML private Label waveIn;
    @FXML private Label fist;
    @FXML private Label fingersSpread;
    private ObservableList<Label> labels;

    private static final String LABEL_HIGHLIGHT = "label-highlight";
    private static final String LABEL_GENERATED = "label-generated";

    private final ModeType modeType = ModeType.TUTORIAL;
    private final TutorialMode mode = (TutorialMode) ModeFactory.createMode(modeType);
    private final TutorialProperties properties = mode.getProperties();
    private final TutorialCollector dataCollector = mode.getDataCollector();
    private final ChangeListener<PoseType> poseTypeChangeListener = createPoseTypeChangeListener(properties.getWantedPoseType());

    public void initialize(URL location, ResourceBundle resources) {
        labels = FXCollections.observableArrayList();
        labels.addAll(Arrays.asList(doubleTap,
                waveIn, waveOut, fist, fingersSpread));
    }

    public void startMode() {
        mode.startMode();
        mode.generateRandomPose();
        highlightPose(properties.getWantedPoseType().getValue(), "label-generated");
        properties.getPoseType().addListener(poseTypeChangeListener);
    }

    public void stopMode() {
        mode.stopMode();
        properties.getPoseType().removeListener(poseTypeChangeListener);
        unlightPose(properties.getWantedPoseType().get(), LABEL_GENERATED);
        unlightPose(properties.getPoseType().get(), LABEL_GENERATED);
    }

    private ChangeListener<PoseType> createPoseTypeChangeListener(ObjectProperty<PoseType> wantedPoseType) {
        return (observable, oldValue, newValue) -> {
            if (newValue == (wantedPoseType.getValue())) {
                unlightPose(newValue, LABEL_GENERATED);
                mode.generateRandomPose();
                highlightPose(wantedPoseType.getValue(), LABEL_GENERATED);
            }
            unlightPose(oldValue, LABEL_HIGHLIGHT);
            highlightPose(newValue, LABEL_HIGHLIGHT);
        };
    }

    private void highlightPose(PoseType poseType, String classStyleName) {
        Platform.runLater(() -> {
                    labels.stream()
                            .filter(label -> poseType.toString().equals(label.getId().toUpperCase()))
                            .findFirst()
                            .ifPresent(label -> label.getStyleClass().add(classStyleName));
                }
        );
    }

    private void unlightPose(PoseType poseType, String classStyleName) {
        Platform.runLater(() -> {
                    labels.stream()
                            .filter(label -> poseType.toString().equals(label.getId().toUpperCase()))
                            .findFirst()
                            .ifPresent(label -> label.getStyleClass().remove(classStyleName));
                }
        );
    }
}
