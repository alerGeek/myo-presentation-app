package pl.pollub.factory.modes;

import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.AbstractDataCollector;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

public class CommunicateMode extends AbstractMode {

    private final DeviceFacade deviceFacade;
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public CommunicateMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.COMMUNICATE_COLLECTOR, properties, dataCollector);
        this.deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();
        this.modeType = ModeType.COMMUNICATE_COLLECTOR;
        this.properties = properties;
        this.dataCollector = dataCollector;
    }

    @Override
    public void create() {
        System.out.println("Tworzę " + modeType);
    }

    public void startMode() {
        super.startMode();
    }

    public void stopMode() {
        super.stopMode();
    }
}
