package pl.pollub.model.factory.modes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.RobotCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.RobotProperties;

@Setter
@Getter
@EqualsAndHashCode
public class RobotMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public RobotMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.PRESENTATION, properties, dataCollector);
        this.modeType = ModeType.PRESENTATION;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    @Override
    public void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    public RobotProperties getProperties() {
        return (RobotProperties) properties;
    }

    public RobotCollector getDataCollector() {
        return (RobotCollector) dataCollector;
    }

}
