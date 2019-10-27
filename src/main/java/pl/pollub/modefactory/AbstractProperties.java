package pl.pollub.modefactory;

import javafx.beans.property.BooleanProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractProperties {
    private final BooleanProperty isActive;

    public AbstractProperties(BooleanProperty isActive) {
        this.isActive = isActive;
    }
}
