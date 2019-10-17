package pl.pollub.factory.modes;

import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.AbstractDataCollector;
import pl.pollub.factory.collectors.CollectorsFactory;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.factory.properties.PropertiesFactory;

public class ModeFactory {
    private final PropertiesFactory propertiesFactory;
    private final CollectorsFactory collectorsFactory;

    public ModeFactory(PropertiesFactory propertiesFactory, CollectorsFactory collectorsFactory) {
        this.propertiesFactory = propertiesFactory;
        this.collectorsFactory = collectorsFactory;
    }

    public AbstractMode createMode(ModeType modeType) {
        AbstractMode createdMode = null;
        AbstractProperties properties;
        AbstractDataCollector dataCollector;
        switch (modeType) {
            case DETAILS_COLLECTOR:
                properties = propertiesFactory.create(modeType);
                dataCollector = collectorsFactory.createCollector(modeType, properties);
                return new DetailsMode(properties, dataCollector);
            case MOUSE_COLLECTOR:
                properties = propertiesFactory.create(modeType);
                dataCollector = collectorsFactory.createCollector(modeType, properties);
                return new MouseMode(properties, dataCollector);
            case ROBOT_COLLECTOR:
                properties = propertiesFactory.create(modeType);
                dataCollector = collectorsFactory.createCollector(modeType, properties);
                return new RobotMode(properties, dataCollector);
            case TUTORIAL_COLLECTOR:
                properties = propertiesFactory.create(modeType);
                dataCollector = collectorsFactory.createCollector(modeType, properties);
                return new TutorialMode(properties, dataCollector);
            case COMMUNICATE_COLLECTOR:
                properties = propertiesFactory.create(modeType);
                dataCollector = collectorsFactory.createCollector(modeType, properties);
                return new CommunicateMode(properties, dataCollector);
            default:
                break;
        }
        return null;
    }
}
