package pl.pollub.model.factory.modes;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.ModeType;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.collectors.TutorialCollector;
import pl.pollub.model.factory.properties.AbstractProperties;
import pl.pollub.model.factory.properties.TutorialProperties;

@Setter
@Getter
@EqualsAndHashCode
public class TutorialMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public TutorialMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.TUTORIAL, properties, dataCollector);
        this.modeType = ModeType.TUTORIAL;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    @Override
    public void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    @Override
    public void startMode() {
        super.startMode();
    }

    @Override
    public void stopMode() {
        super.stopMode();
    }

    public void generateRandomPose() {
        final TutorialProperties tempProperties = (TutorialProperties) properties;
        tempProperties.generateRandomPose();
    }

    public TutorialProperties getProperties() {
        return (TutorialProperties) properties;
    }

    public TutorialCollector getDataCollector() {
        return (TutorialCollector) dataCollector;
    }
}
