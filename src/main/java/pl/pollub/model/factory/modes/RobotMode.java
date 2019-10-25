package pl.pollub.model.factory.modes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.RobotCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.RobotProperties;

@Setter
@Getter
@ToString
public class RobotMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public RobotMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.ROBOT_COLLECTOR, properties, dataCollector);
        this.modeType = ModeType.ROBOT_COLLECTOR;
        this.properties = properties;
        this.dataCollector = dataCollector;
}

public RobotProperties getProperties() {
        return (RobotProperties) properties;
    }

    public RobotCollector getDataCollector() {
        return (RobotCollector) dataCollector;
    }

}
