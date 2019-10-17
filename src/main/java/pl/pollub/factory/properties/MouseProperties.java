package pl.pollub.factory.properties;

import com.thalmic.myo.enums.PoseType;
import javafx.beans.property.*;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MouseProperties extends AbstractProperties {
    private final ObjectProperty<PoseType> lastPose;
    private final LongProperty startTimestamp;
    private final LongProperty duration;
    private final StringProperty currentLocation;

    @Builder
    public MouseProperties(BooleanProperty isActive, ObjectProperty<PoseType> lastPose, LongProperty startTimestamp, LongProperty duration, StringProperty currentLocation) {
        super(isActive);
        this.lastPose = lastPose;
        this.startTimestamp = startTimestamp;
        this.duration = duration;
        this.currentLocation = currentLocation;
    }

    public static MouseProperties createProperties() {
        return MouseProperties.builder()
                .isActive(new SimpleBooleanProperty())
                .lastPose(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .duration(new SimpleLongProperty())
                .startTimestamp(new SimpleLongProperty())
                .currentLocation(new SimpleStringProperty("0 x 0"))
                .build();
    }
}
