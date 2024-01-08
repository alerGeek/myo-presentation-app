package pl.pollub.util;

import lombok.Getter;
import pl.pollub.device.CollectorService;
import pl.pollub.device.Connector;
import pl.pollub.device.Device;
import pl.pollub.device.DeviceFacade;

@Getter
public enum FacadeWrapperSingleton {
    INSTANCE;

    private final DeviceFacade deviceFacade;

    FacadeWrapperSingleton() {
        Device device = Device.create();
        CollectorService collector = new CollectorService(device, device.getCommunicate());
        Connector connector = new Connector(device);

        deviceFacade = new DeviceFacade(device, collector, connector);
    }
}
