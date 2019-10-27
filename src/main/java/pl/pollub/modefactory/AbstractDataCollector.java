package pl.pollub.modefactory;

import com.thalmic.myo.AbstractDeviceListener;
import lombok.Getter;

@Getter
public abstract class AbstractDataCollector extends AbstractDeviceListener {
    private final String collectorName;
    private final AbstractProperties properties;

    public AbstractDataCollector(String collectorName, AbstractProperties properties) {
        this.collectorName = collectorName;
        this.properties = properties;
    }
}
