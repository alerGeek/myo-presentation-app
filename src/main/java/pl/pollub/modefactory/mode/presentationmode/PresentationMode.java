package pl.pollub.modefactory.mode.presentationmode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.modefactory.AbstractMode;
import pl.pollub.types.ModeType;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractProperties;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
public class PresentationMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public PresentationMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.PRESENTATION, properties, dataCollector);
        this.modeType = ModeType.PRESENTATION;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    @Override
    public void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    public PresentationProperties getProperties() {
        return (PresentationProperties) properties;
    }

    public PresentationCollector getDataCollector() {
        return (PresentationCollector) dataCollector;
    }

}
