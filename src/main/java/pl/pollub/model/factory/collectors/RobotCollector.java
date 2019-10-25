package pl.pollub.model.factory.collectors;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import lombok.Getter;
import pl.pollub.model.factory.collectors.posestrategy.KeyActionContext;
import pl.pollub.model.factory.collectors.posestrategy.LeftKeyAction;
import pl.pollub.model.factory.collectors.posestrategy.RightKeyAction;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.RobotProperties;

@Getter
public class RobotCollector extends AbstractDataCollector {
    private final RobotProperties properties;
    private final KeyActionContext keyActionContext;
    private final LeftKeyAction leftKeyAction;
    private final RightKeyAction rightKeyAction;

    public RobotCollector(String collectorName, AbstractProperties properties, KeyActionContext keyActionContext, LeftKeyAction leftKeyAction, RightKeyAction rightKeyAction) {
        super(collectorName, properties);
        this.properties = (RobotProperties) properties;
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

    public RobotProperties getProperties() {
        return properties;
    }
}
