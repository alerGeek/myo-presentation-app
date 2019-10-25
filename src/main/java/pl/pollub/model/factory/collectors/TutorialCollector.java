package pl.pollub.model.factory.collectors;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.enums.VibrationType;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.TutorialProperties;

public class TutorialCollector extends AbstractDataCollector {
    private final TutorialProperties properties;

    public TutorialCollector(String collectorName, AbstractProperties properties) {
        super(collectorName, properties);
        this.properties = (TutorialProperties) properties;
    }

    public void onPose(Myo myo, long timestamp, Pose pose) {
        if (properties.getWantedPoseType().get() == pose.getType()) {
            myo.vibrate(VibrationType.VIBRATION_SHORT);
        }
        properties.getPoseType().set(pose.getType());
    }

    public TutorialProperties getProperties() {
        return properties;
    }
}
