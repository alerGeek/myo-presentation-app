package pl.pollub.factory.properties;

import pl.pollub.factory.ModeType;

public class PropertiesFactory {

    public AbstractProperties create(ModeType modeType) {
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

            case COMMUNICATE_COLLECTOR:
                createdProperties = CommunicateProperties.createProperties();
                break;

            default:
                break;
        }
        return createdProperties;
    }
}
