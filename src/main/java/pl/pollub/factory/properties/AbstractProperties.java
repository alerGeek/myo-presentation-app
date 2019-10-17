package pl.pollub.factory.properties;

import javafx.beans.property.BooleanProperty;
import lombok.Getter;

@Getter
public abstract class AbstractProperties {
    private final BooleanProperty isActive;

    public AbstractProperties(BooleanProperty isActive) {
        this.isActive = isActive;
    }
}
