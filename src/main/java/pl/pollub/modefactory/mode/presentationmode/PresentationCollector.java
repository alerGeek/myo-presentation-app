package pl.pollub.modefactory.mode.presentationmode;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import lombok.Getter;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractProperties;
import pl.pollub.modefactory.posestrategy.KeyActionContext;
import pl.pollub.modefactory.posestrategy.LeftKeyAction;
import pl.pollub.modefactory.posestrategy.RightKeyAction;

@Getter
public class PresentationCollector extends AbstractDataCollector {
    private final PresentationProperties properties;
    private final KeyActionContext keyActionContext;
    private final LeftKeyAction leftKeyAction;
    private final RightKeyAction rightKeyAction;

    public PresentationCollector(String collectorName, AbstractProperties properties, KeyActionContext keyActionContext, LeftKeyAction leftKeyAction, RightKeyAction rightKeyAction) {
        super(collectorName, properties);
        this.properties = (PresentationProperties) properties;
        this.keyActionContext = keyActionContext;
        this.leftKeyAction = leftKeyAction;
        this.rightKeyAction = rightKeyAction;
    }

    public void onPose(Myo myo, long timestamp, Pose pose) {
        properties.getPoseType().setValue(pose.getType());

        switch (pose.getType()) {
            case WAVE_OUT:
                keyActionContext.set(rightKeyAction);
                keyActionContext.simulateKeyEvent();
                break;
            case WAVE_IN:
                keyActionContext.set(leftKeyAction);
                keyActionContext.simulateKeyEvent();
                break;
            case DOUBLE_TAP:
                if (!properties.getIsLocked().get())
                    myo.lock();
                break;
        }
    }

    public PresentationProperties getProperties() {
        return properties;
    }
}
