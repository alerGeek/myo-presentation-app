package pl.pollub.device;

import com.thalmic.myo.exception.HubNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import pl.pollub.modefactory.AbstractDataCollector;

@AllArgsConstructor
@Getter
public class DeviceFacade {
    private final Device device;
    private final CollectorService collectorService;
    private final Connector connector;

    public void connectMyo() {
        this.connector.connectMyo();
    }

    public void collectData() {
        this.collectorService.restart();
    }

    public void stopCollectData() {
        this.collectorService.cancel();
    }

    public void addDataCollector(AbstractDataCollector dataCollector) {
        try {
            device.addDataCollector(dataCollector);
        } catch (HubNotFoundException e) {
            device.getCommunicate().setValue(e.getMessage());
        }
    }

    public void removeDataCollector(AbstractDataCollector dataCollector) {
        try {
            device.removeDataCollector(dataCollector);
        } catch (HubNotFoundException e) {
            device.getCommunicate().setValue(e.getMessage());
        }
    }
}

