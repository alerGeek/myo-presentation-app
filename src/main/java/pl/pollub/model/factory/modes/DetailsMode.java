package pl.pollub.model.factory.modes;

import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.DetailsCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.DetailsProperties;

public class DetailsMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public DetailsMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.DETAILS_COLLECTOR, properties, dataCollector);
        this.modeType = ModeType.DETAILS_COLLECTOR;
        this.properties = properties;
        this.dataCollector = dataCollector;
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
