package pl.pollub.model.factory.collectors;

import com.thalmic.myo.AbstractDeviceListener;
import lombok.Getter;
import pl.pollub.model.factory.properties.AbstractProperties;

@Getter
public abstract class AbstractDataCollector extends AbstractDeviceListener {
    private final String collectorName;
    private final AbstractProperties properties;

    public AbstractDataCollector(String collectorName, AbstractProperties properties) {
        this.collectorName = collectorName;
        this.properties = properties;
    }
}
