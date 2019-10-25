package pl.pollub.model.factory.modes;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.MouseCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.MouseProperties;

@Setter
@Getter
public class MouseMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public MouseMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.MOUSE_COLLECTOR, properties, dataCollector);
        this.modeType = ModeType.MOUSE_COLLECTOR;
        this.properties = properties;
        this.dataCollector = dataCollector;
}

public MouseProperties getProperties() {
        return (MouseProperties) properties;
    }

    public MouseCollector getDataCollector() {
        return (MouseCollector) dataCollector;
    }

}
