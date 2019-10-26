package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class KeyActionStrategy {
    private int keyCode;

    abstract int pressKey();

    abstract int pressMouseKey();

}