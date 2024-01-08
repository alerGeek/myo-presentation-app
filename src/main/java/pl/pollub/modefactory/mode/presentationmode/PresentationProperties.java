package pl.pollub.modefactory.mode.presentationmode;

import com.thalmic.myo.enums.PoseType;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import lombok.Builder;
import lombok.Getter;
import pl.pollub.modefactory.AbstractProperties;

@Getter
public class PresentationProperties extends AbstractProperties {
    private final ObjectProperty<PoseType> poseType;
    private final BooleanProperty isLocked;

    @Builder
    public PresentationProperties(BooleanProperty isActive, ObjectProperty<PoseType> poseType, BooleanProperty isLocked) {
        super(isActive);
        this.poseType = poseType;
        this.isLocked = isLocked;
    }

    public static PresentationProperties createProperties() {
        return PresentationProperties.builder()
                .isActive(new SimpleBooleanProperty())
                .poseType(new SimpleObjectProperty<>(PoseType.UNKNOWN))
                .isLocked(new SimpleBooleanProperty())
                .build();
    }
}
