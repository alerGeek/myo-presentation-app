package pl.pollub.factory.properties;

import com.thalmic.myo.enums.PoseType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TutorialProperties extends AbstractProperties {
    private final ObjectProperty<PoseType> poseType;
    private final ObjectProperty<PoseType> wantedPoseType;

    @Builder
    public TutorialProperties(BooleanProperty isActive, ObjectProperty<PoseType> poseType, ObjectProperty<PoseType> wantedPoseType) {
        super(isActive);
        this.poseType = poseType;
        this.wantedPoseType = wantedPoseType;
    }

    public static TutorialProperties createProperties() {
        return TutorialProperties.builder()
                .isActive(new SimpleBooleanProperty())
                .poseType(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .wantedPoseType(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .build();
    }
}
