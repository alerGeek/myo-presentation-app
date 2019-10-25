package pl.pollub.model.factory.modes;

import com.thalmic.myo.exception.HubNotFoundException;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

@Setter
@Getter
public abstract class AbstractMode {
    private final DeviceFacade deviceFacade;
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public AbstractMode(ModeType modeType, AbstractProperties properties, AbstractDataCollector dataCollector) {
        this.deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
        this.modeType = modeType;
        this.properties = properties;
        this.dataCollector = dataCollector;
    }

    public void startMode() {
        try {
            deviceFacade.getDevice().addDataCollector(dataCollector);
        } catch (HubNotFoundException e) {
            deviceFacade.getDevice().getCommunicate().setValue(e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopMode() {
        deviceFacade.getDevice().removeDataCollector(dataCollector);
    }
}
