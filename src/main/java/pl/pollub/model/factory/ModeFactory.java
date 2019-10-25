package pl.pollub.model.factory;

import pl.pollub.model.factory.collectors.*;
import pl.pollub.model.factory.collectors.mousemover.MouseMover;
import pl.pollub.model.factory.collectors.posestrategy.*;
import pl.pollub.model.factory.modes.*;
import pl.pollub.model.factory.properties.*;

import java.util.Arrays;
import java.util.HashMap;

public class ModeFactory {
    public static AbstractProperties createProperties(ModeType modeType) {
        AbstractProperties createdProperties = null;
        switch (modeType) {
            case DETAILS:
                createdProperties = DetailsProperties.createProperties();
                break;
            case MOUSE:
                createdProperties = MouseProperties.createProperties();
                break;
            case PRESENTATION:
                createdProperties = RobotProperties.createProperties();
                break;
            case TUTORIAL:
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
            case DETAILS:
                createdCollector = new DetailsCollector(modeType.toLower(), properties);
                break;
            case MOUSE:
                createdCollector = new MouseCollector(modeType.toLower(), properties, new KeyActionContext(), new MouseMover(), new MouseLeftClickAction(), new MouseRightClickAction());
                break;
            case PRESENTATION:
                createdCollector = new RobotCollector(modeType.toLower(), properties, new KeyActionContext(), new LeftKeyAction(), new RightKeyAction());
                break;
            case TUTORIAL:
                createdCollector = new TutorialCollector(modeType.toLower(), properties);
                break;
            default:
                break;
        }
        return createdCollector;
    }

    public static AbstractMode createMode(ModeType modeType) {
        AbstractProperties properties = createProperties(modeType);
        AbstractDataCollector dataCollector = createCollector(modeType, properties);
        AbstractMode mode = null;
        switch (modeType) {
            case DETAILS:
                mode = new DetailsMode(properties, dataCollector);
                break;
            case MOUSE:
                mode = new MouseMode(properties, dataCollector);
                break;
            case PRESENTATION:
                mode = new RobotMode(properties, dataCollector);
                break;
            case TUTORIAL:
                mode = new TutorialMode(properties, dataCollector);
                break;
            case COMMUNICATE:
                break;
            default:
                throw new IllegalArgumentException("There is no mode implementation of modeType = " + mode);
        }
        return mode;
    }

    public static HashMap<String, AbstractMode> createAllModes() {
        HashMap<String, AbstractMode> modesMap = new HashMap<>();

        Arrays.stream(ModeType.values())
                .forEach(modeType -> modesMap.put(modeType.toLower(), createMode(modeType)));
        return modesMap;
    }
}
