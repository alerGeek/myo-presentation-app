package pl.pollub.factory.collectors;

import pl.pollub.factory.ModeType;
import pl.pollub.factory.properties.AbstractProperties;
import pl.pollub.model.MouseMover;
import pl.pollub.model.posestrategy.KeyActionContext;
import pl.pollub.model.posestrategy.LeftKeyAction;
import pl.pollub.model.posestrategy.RightKeyAction;

public class CollectorsFactory {

    public AbstractDataCollector createCollector(ModeType modeType, AbstractProperties properties) {
        AbstractDataCollector createdCollector = null;
        switch (modeType) {
            case DETAILS_COLLECTOR:
                createdCollector = new DetailsCollector(properties);
                break;

            case MOUSE_COLLECTOR:
                createdCollector = new MouseCollector(properties, new KeyActionContext(), new MouseMover());
                break;

            case ROBOT_COLLECTOR:
                createdCollector = new RobotCollector(properties, new KeyActionContext(), new LeftKeyAction(), new RightKeyAction());
                break;

            case TUTORIAL_COLLECTOR:
                createdCollector = new TutorialCollector(properties);
                break;
            case COMMUNICATE_COLLECTOR:
                createdCollector = new CommunicateCollector(properties);
                break;

            default:
                break;
        }
        return createdCollector;
    }
}
