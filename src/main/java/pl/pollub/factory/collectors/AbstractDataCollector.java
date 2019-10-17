package pl.pollub.factory.collectors;

import com.thalmic.myo.AbstractDeviceListener;
import lombok.Getter;
import pl.pollub.factory.properties.AbstractProperties;

@Getter
public abstract class AbstractDataCollector extends AbstractDeviceListener {
    private final AbstractProperties properties;

    public AbstractDataCollector(AbstractProperties properties) {
        this.properties = properties;
    }
}
