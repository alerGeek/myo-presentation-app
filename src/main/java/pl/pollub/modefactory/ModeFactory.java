package pl.pollub.modefactory;

import pl.pollub.modefactory.mode.detailsmode.DetailsCollector;
import pl.pollub.modefactory.mode.detailsmode.DetailsMode;
import pl.pollub.modefactory.mode.detailsmode.DetailsProperties;
import pl.pollub.modefactory.mode.mousemode.MouseCollector;
import pl.pollub.modefactory.mode.mousemode.MouseMode;
import pl.pollub.modefactory.mode.mousemode.MouseMover;
import pl.pollub.modefactory.mode.mousemode.MouseProperties;
import pl.pollub.modefactory.mode.presentationmode.PresentationCollector;
import pl.pollub.modefactory.mode.presentationmode.PresentationMode;
import pl.pollub.modefactory.mode.presentationmode.PresentationProperties;
import pl.pollub.modefactory.mode.tutorialmode.TutorialCollector;
import pl.pollub.modefactory.mode.tutorialmode.TutorialMode;
import pl.pollub.modefactory.mode.tutorialmode.TutorialProperties;
import pl.pollub.modefactory.posestrategy.*;
import pl.pollub.type.ModeType;

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
                createdProperties = PresentationProperties.createProperties();
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
                createdCollector = new PresentationCollector(modeType.toLower(), properties, new KeyActionContext(), new LeftKeyAction(), new RightKeyAction());
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
                mode = new PresentationMode(properties, dataCollector);
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
