package pl.pollub.factory.collectors;

import com.thalmic.myo.FirmwareVersion;
import com.thalmic.myo.Myo;
import com.thalmic.myo.enums.WarmupResult;
import lombok.Builder;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.factory.properties.CommunicateProperties;
import pl.pollub.model.MyoState;

public class CommunicateCollector extends AbstractDataCollector {
    private CommunicateProperties properties;

    @Builder
    public CommunicateCollector(AbstractProperties properties) {
        super(properties);
        this.properties = (CommunicateProperties) properties;
    }


    public void onConnect(Myo myo, long timestamp, FirmwareVersion firmwareVersion) {
        properties.getNewCommunicate().set(MyoState.MYO_CONNECTED.getCommunicateText());
    }

    public void onDisconnect(Myo myo, long timestamp) {
        properties.getNewCommunicate().set(MyoState.MYO_DISCONNECTED.getCommunicateText());
    }

    public void onWarmupCompleted(Myo myo, long timestamp, WarmupResult warmupResult) {
        if (warmupResult.equals(WarmupResult.WARMUP_RESULT_SUCCESS)) {
            properties.getNewCommunicate().set(MyoState.MYO_WARMED_UP.getCommunicateText());
        }
    }
}
