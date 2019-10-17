package pl.pollub.factory.collectors;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.factory.properties.TutorialProperties;
import pl.pollub.utils.RandomEnumGenerator;

public class TutorialCollector extends AbstractDataCollector {
    private final TutorialProperties properties;

    public TutorialCollector(AbstractProperties properties) {
        super(properties);
        this.properties = (TutorialProperties) properties;
    }

    public void onPose(Myo myo, long timestamp, Pose pose) {
        if (properties.getWantedPoseType().get() == pose.getType()) {
            myo.vibrate(VibrationType.VIBRATION_SHORT);
        }
        properties.getPoseType().set(pose.getType());
    }

    public void generateRandomPose() {
        properties.getWantedPoseType().set(RandomEnumGenerator.randomEnum(PoseType.class));
        if (properties.getWantedPoseType().get() == PoseType.UNKNOWN || properties.getWantedPoseType().get() == PoseType.REST)
            generateRandomPose();
    }

}
