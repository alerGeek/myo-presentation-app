package pl.pollub.factory.modes;

import com.thalmic.myo.exception.HubNotFoundException;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.AbstractDataCollector;
import pl.pollub.factory.properties.AbstractProperties;
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

    abstract void create();

    public void startMode(){
        try {
            deviceFacade.getDevice().addDataCollector(dataCollector);
        } catch (HubNotFoundException e) {
            deviceFacade.getDevice().getCommunicate().setValue(e.getMessage());
            e.printStackTrace();
        }
    }

    public void stopMode(){
        try {
            deviceFacade.getDevice().removeDataCollector(dataCollector);
        } catch (HubNotFoundException e) {
            deviceFacade.getDevice().getCommunicate().setValue(e.getMessage());
            e.printStackTrace();
        }
    }
}
