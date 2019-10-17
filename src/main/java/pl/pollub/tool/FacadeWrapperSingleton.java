package pl.pollub.tool;

import lombok.Getter;
import pl.pollub.model.fasade.*;

public enum FacadeWrapperSingleton {
    INSTANCE;

    @Getter
    private final DeviceFacade facade;

    FacadeWrapperSingleton() {
        Device device = Device.create();
        CollectorService collector = new CollectorService(device, device.getCommunicate());
        Connector connector = new Connector(device, new FindMyoTask(device));

        facade = new DeviceFacade(device, collector, connector);
    }
}