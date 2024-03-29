package pl.pollub.modefactory.mode.detailsmode;

import lombok.EqualsAndHashCode;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractMode;
import pl.pollub.modefactory.AbstractProperties;
import pl.pollub.type.ModeType;

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

    public void addToDeviceFacade() {
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
