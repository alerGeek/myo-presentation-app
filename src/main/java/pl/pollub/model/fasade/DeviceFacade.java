package pl.pollub.model.fasade;

import lombok.AllArgsConstructor;
import lombok.Getter;

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
}

