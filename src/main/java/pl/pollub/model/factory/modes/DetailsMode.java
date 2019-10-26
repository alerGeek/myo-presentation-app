package pl.pollub.model.factory.modes;

import lombok.EqualsAndHashCode;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.DetailsCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.DetailsProperties;

@EqualsAndHashCode(callSuper = true)
public class DetailsMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public DetailsMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.DETAILS, properties, dataCollector);
        modeType = ModeType.DETAILS;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    @Override
    public void startMode() {
        super.startMode();
    }

    @Override
    public void stopMode() {
        super.stopMode();
    }

    public DetailsProperties getProperties() {
        return (DetailsProperties) properties;
    }

    public DetailsCollector getDataCollector() {
        return (DetailsCollector) dataCollector;
    }
}
