package pl.pollub.model.factory;

import pl.pollub.model.factory.collectors.*;
import pl.pollub.model.factory.collectors.mousemover.MouseMover;
import pl.pollub.model.factory.collectors.posestrategy.*;
import pl.pollub.model.factory.modes.*;
import pl.pollub.model.factory.properties.*;

public class ModeFactory {
    public static AbstractProperties createProperties(ModeType modeType) {
        AbstractProperties createdProperties = null;
        switch (modeType) {
            case DETAILS_COLLECTOR:
                createdProperties = DetailsProperties.createProperties();
                break;
            case MOUSE_COLLECTOR:
                createdProperties = MouseProperties.createProperties();
                break;
            case ROBOT_COLLECTOR:
                createdProperties = RobotProperties.createProperties();
                break;
            case TUTORIAL_COLLECTOR:
                createdProperties = TutorialProperties.createProperties();
                break;
            default:
                break;
        }
        return createdProperties;
    }

    public static AbstractDataCollector createCollector(ModeType modeType, AbstractProperties properties) {
        AbstractDataCollector createdCollector = null;
        switch (modeType) {
            case DETAILS_COLLECTOR:
                createdCollector = new DetailsCollector("details", properties);
                break;
            case MOUSE_COLLECTOR:
                createdCollector = new MouseCollector("mouse", properties, new KeyActionContext(), new MouseMover(), new MouseLeftClickAction(), new MouseRightClickAction());
                break;
            case ROBOT_COLLECTOR:
                createdCollector = new RobotCollector("robot", properties, new KeyActionContext(), new LeftKeyAction(), new RightKeyAction());
                break;
            case TUTORIAL_COLLECTOR:
                createdCollector = new TutorialCollector("tutorial", properties);
                break;
            default:
                break;
        }
        return createdCollector;
    }

    public static AbstractMode createMode(ModeType modeType) {
        AbstractProperties properties = createProperties(modeType);
        AbstractDataCollector dataCollector = createCollector(modeType, properties);
        switch (modeType) {
            case DETAILS_COLLECTOR:
                return new DetailsMode(properties, dataCollector);
            case MOUSE_COLLECTOR:
                return new MouseMode(properties, dataCollector);
            case ROBOT_COLLECTOR:
                return new RobotMode(properties, dataCollector);
            case TUTORIAL_COLLECTOR:
                return new TutorialMode(properties, dataCollector);
            default:
                break;
        }
        throw new IllegalArgumentException("Nie ma takiego trybu");
    }
}
