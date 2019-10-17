package pl.pollub.factory.collectors;

import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.enums.*;
import lombok.Builder;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.factory.properties.DetailsProperties;
import pl.pollub.model.MyoState;

public class DetailsCollector extends AbstractDataCollector {
    private DetailsProperties properties;

    @Builder
    public DetailsCollector(AbstractProperties properties) {
        super(properties);
        this.properties = (DetailsProperties) properties;
    }

    public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation, WarmupState warmupState) {
        properties.getWhichArm().set(arm);
    }

    public void onArmUnsync(Myo myo, long timestamp) {
        properties.getWhichArm().set(Arm.ARM_UNKNOWN);
    }

    public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
        properties.getConnectState().set(MyoState.MYO_CONNECTED);
//        properties.getCommunicate().set(MyoState.MYO_CONNECTED.getCommunicateText());
    }

    public void onDisconnect(Myo myo, long timestamp) {
        properties.getConnectState().set(MyoState.MYO_DISCONNECTED);
//        properties.getCommunicate().set(MyoState.MYO_DISCONNECTED.getCommunicateText());
    }

    public void onWarmupCompleted(Myo myo, long timestamp, WarmupResult warmupResult) {
        if (warmupResult.equals(WarmupResult.WARMUP_RESULT_SUCCESS)) {
//            properties.getCommunicate().set(MyoState.MYO_WARMED_UP.getCommunicateText());
        }
    }

    public void onPose(Myo myo, long timestamp, Pose pose) {
        properties.getPoseType().set(pose.getType());
    }

    public void onLock(Myo myo, long timestamp) {
        properties.getIsLocked().set(true);
    }

    public void onUnlock(Myo myo, long timestamp) {
        properties.getIsLocked().set(false);
        myo.unlock(UnlockType.UNLOCK_HOLD);
    }

    public void onBatteryLevelReceived(Myo myo, long timestamp, int level) {
        properties.getBatteryLevel().set(level);
    }

}
