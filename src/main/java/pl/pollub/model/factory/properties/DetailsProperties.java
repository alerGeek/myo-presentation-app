package pl.pollub.model.factory.properties;

import com.thalmic.myo.enums.Arm;
import com.thalmic.myo.enums.PoseType;
import javafx.beans.property.*;
import lombok.Builder;
import lombok.Getter;
import pl.pollub.model.MyoState;

@Getter
public class DetailsProperties extends AbstractProperties {
    private final ObjectProperty<MyoState> connectState;
    private final ObjectProperty<Arm> whichArm;
    private final ObjectProperty<PoseType> poseType;
    private final BooleanProperty isLocked;
    private final IntegerProperty batteryLevel;

    @Builder
    public DetailsProperties(BooleanProperty isActive, ObjectProperty<MyoState> connectState, ObjectProperty<Arm> whichArm, ObjectProperty<PoseType> poseType, BooleanProperty isLocked, IntegerProperty batteryLevel) {
        super(isActive);
        this.connectState = connectState;
        this.whichArm = whichArm;
        this.poseType = poseType;
        this.isLocked = isLocked;
        this.batteryLevel = batteryLevel;
    }

    public static DetailsProperties createProperties() {
        return DetailsProperties.builder()
                .batteryLevel(new SimpleIntegerProperty(0))
                .connectState(new SimpleObjectProperty<>(MyoState.MYO_UNKNOWN))
                .isActive(new SimpleBooleanProperty())
                .isLocked(new SimpleBooleanProperty())
                .poseType(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .whichArm(new SimpleObjectProperty<>(Arm.ARM_UNKNOWN))
                .build();
    }
}
