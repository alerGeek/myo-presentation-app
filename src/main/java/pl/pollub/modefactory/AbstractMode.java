package pl.pollub.modefactory;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.types.ModeType;
import pl.pollub.device.DeviceFacade;
import pl.pollub.util.FacadeWrapperSingleton;

@Setter
@Getter
public abstract class AbstractMode {
    private final DeviceFacade deviceFacade;
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public AbstractMode(ModeType modeType, AbstractProperties properties, AbstractDataCollector dataCollector) {
        this.deviceFacade = FacadeWrapperSingleton.INSTANCE.getDeviceFacade();
        this.modeType = modeType;
        this.properties = properties;
        this.dataCollector = dataCollector;
    }

    public abstract void addToDeviceFacade();

    public void startMode() {
        deviceFacade.addDataCollector(dataCollector);
    }

    public void stopMode() {
        deviceFacade.removeDataCollector(dataCollector);
    }
}
