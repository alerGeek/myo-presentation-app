package pl.pollub.model.factory.modes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.MouseCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.MouseProperties;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)

public class MouseMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public MouseMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.MOUSE, properties, dataCollector);
        this.modeType = ModeType.MOUSE;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    @Override
    public void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    public MouseProperties getProperties() {
        return (MouseProperties) properties;
    }

    public MouseCollector getDataCollector() {
        return (MouseCollector) dataCollector;
    }

}
