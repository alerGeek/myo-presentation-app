package pl.pollub.modefactory.mode.mousemode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.modefactory.AbstractDataCollector;
import pl.pollub.modefactory.AbstractMode;
import pl.pollub.modefactory.AbstractProperties;
import pl.pollub.type.ModeType;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)

public class MouseMode extends AbstractMode {
    private final ModeType modeType;
    private final AbstractProperties properties;
    private final AbstractDataCollector dataCollector;

    public MouseMode(AbstractProperties properties, AbstractDataCollector dataCollector) {
        super(ModeType.MOUSE, properties, dataCollector);
        this.modeType = ModeType.MOUSE;
        this.properties = properties;
        this.dataCollector = dataCollector;
        addToDeviceFacade();
    }

    @Override
    public void addToDeviceFacade() {
        super.getDeviceFacade().getDevice().getModesMap().put(modeType.toLower(), this);
    }

    public MouseProperties getProperties() {
        return (MouseProperties) properties;
    }

    public MouseCollector getDataCollector() {
        return (MouseCollector) dataCollector;
    }

}
