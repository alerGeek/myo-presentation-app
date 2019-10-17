package pl.pollub.factory.collectors;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.factory.properties.RobotProperties;
import pl.pollub.model.posestrategy.KeyActionContext;
import pl.pollub.model.posestrategy.LeftKeyAction;
import pl.pollub.model.posestrategy.RightKeyAction;

public class RobotCollector extends AbstractDataCollector {
    private final RobotProperties properties;
    private final KeyActionContext keyActionContext;
    private final LeftKeyAction leftKeyAction;
    private final RightKeyAction rightKeyAction;

    public RobotCollector(AbstractProperties properties, KeyActionContext keyActionContext, LeftKeyAction leftKeyAction, RightKeyAction rightKeyAction) {
        super(properties);
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
                keyActionContext.doAction();
                break;
            case WAVE_IN:
                keyActionContext.set(leftKeyAction);
                keyActionContext.doAction();
                break;
            case DOUBLE_TAP:
                if (!properties.getIsLocked().get())
                    myo.lock();
                break;
        }
    }
}
