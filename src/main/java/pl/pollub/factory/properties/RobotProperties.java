package pl.pollub.factory.properties;

import com.thalmic.myo.enums.PoseType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RobotProperties extends AbstractProperties {
    private final ObjectProperty<PoseType> poseType;
    private final BooleanProperty isLocked;

    @Builder
    public RobotProperties(BooleanProperty isActive, ObjectProperty<PoseType> poseType, BooleanProperty isLocked) {
        super(isActive);
        this.poseType = poseType;
        this.isLocked = isLocked;
    }

    public static RobotProperties createProperties() {
        return RobotProperties.builder()
                .isActive(new SimpleBooleanProperty())
                .poseType(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .build();
    }
}
