package pl.pollub.modefactory.mode.mousemode;

import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.Vector3;
import com.thalmic.myo.enums.*;
import javafx.application.Platform;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import lombok.Getter;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractProperties;
import pl.pollub.modefactory.posestrategy.KeyActionContext;
import pl.pollub.modefactory.posestrategy.MouseLeftClickAction;
import pl.pollub.modefactory.posestrategy.MouseRightClickAction;
import pl.pollub.util.RobotManager;

import java.awt.*;
import java.util.concurrent.TimeUnit;

@Getter
public class MouseCollector extends AbstractDataCollector {
    private final MouseProperties properties;
    private final KeyActionContext keyActionContext;
    private final MouseLeftClickAction leftClickAction;
    private final MouseRightClickAction rightClickAction;
    private final MouseMover mouseMover;
    private final ScheduledService<Void> currentPositionService;

    public MouseCollector(String collectorName, AbstractProperties properties, KeyActionContext keyActionContext, MouseMover mouseMover, MouseLeftClickAction leftClickAction, MouseRightClickAction rightClickAction) {
        super(collectorName, properties);
        this.properties = (MouseProperties) properties;
        this.keyActionContext = keyActionContext;
        this.mouseMover = mouseMover;
        this.leftClickAction = leftClickAction;
        this.rightClickAction = rightClickAction;

        currentPositionService = createCurrentPositionService();
    }

    public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation, WarmupState warmupState) {
        this.mouseMover.onArm(xDirection);
    }


    public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
        if (!properties.getIsActive().get()) {
            return;
        }
        this.mouseMover.onOrientationData(rotation);
    }

    public void onGyroscopeData(Myo myo, long timestamp, Vector3 gyro) {
        if (!properties.getIsActive().get()) {
            return;
        }
        this.mouseMover.onGyroscope(gyro);
        RobotManager.moveMouse(mouseMover.getDx(), mouseMover.getDy());
    }

    public void onPose(Myo myo, long timestamp, Pose pose) {
        PoseType lastPose = properties.getLastPose().get();

        long timestampMillis = TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.MICROSECONDS);
        long startTimestampMillis = TimeUnit.MILLISECONDS.convert(timestamp, TimeUnit.MICROSECONDS);

        if (pose.getType() == PoseType.FIST) {
            properties.getStartTimestamp().set(startTimestampMillis);
        }
        if (pose.getType() == PoseType.REST && lastPose == PoseType.FIST) {
            properties.getDuration().set(timestampMillis - properties.getStartTimestamp().get());

            if (properties.getDuration().get() > 4000) {
                myo.vibrate(VibrationType.VIBRATION_SHORT);
                if (properties.getIsActive().get()) {
                    properties.getIsActive().set(false);
                } else {
                    properties.getIsActive().set(true);
                }
                properties.getDuration().set(0);
                properties.getStartTimestamp().set(0);
            }
        }
        properties.getLastPose().set(pose.getType());

        if (properties.getIsActive().get() && pose.getType() == PoseType.FINGERS_SPREAD) {
            keyActionContext.set(leftClickAction);
            keyActionContext.simulateMouseEvent();
        }
    }

    private ScheduledService<Void> createCurrentPositionService() {
        return new ScheduledService<Void>() {
            @Override
            public boolean cancel() {
                return super.cancel();
            }

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        Platform.runLater(() -> {
                            properties.getCurrentLocation().set(MouseInfo.getPointerInfo().getLocation().x + " x " + MouseInfo.getPointerInfo().getLocation().y);
                        });
                        return null;
                    }
                };
            }
        };
    }

    @Override
    public MouseProperties getProperties() {
        return properties;
    }
}
