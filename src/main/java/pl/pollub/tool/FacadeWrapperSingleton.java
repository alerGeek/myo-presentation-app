package pl.pollub.tool;

import lombok.Getter;
import pl.pollub.model.fasade.CollectorService;
import pl.pollub.model.fasade.Connector;
import pl.pollub.model.fasade.Device;
import pl.pollub.model.fasade.DeviceFacade;

@Getter
public enum FacadeWrapperSingleton {
    INSTANCE;

    private final DeviceFacade facade;

    FacadeWrapperSingleton() {
        Device device = Device.create();
        CollectorService collector = new CollectorService(device, device.getCommunicate());
        Connector connector = new Connector(device);

        facade = new DeviceFacade(device, collector, connector);
    }
}