package pl.pollub.modefactory.mode.tutorialmode;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.enums.VibrationType;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractProperties;

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
