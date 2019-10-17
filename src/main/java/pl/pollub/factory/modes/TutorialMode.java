package pl.pollub.factory.modes;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.pollub.factory.ModeType;
import pl.pollub.factory.collectors.AbstractDataCollector;
import pl.pollub.factory.properties.AbstractProperties;

@Setter
@Getter
@ToString
public class TutorialMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public TutorialMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.TUTORIAL_COLLECTOR, properties, dataCollector);
        this.modeType = ModeType.TUTORIAL_COLLECTOR;
        this.properties = properties;
        this.dataCollector = dataCollector;
        create();
    }


    @Override
    public void create() {
        System.out.println("Tworzę " + modeType);
    }
}
