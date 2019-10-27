package pl.pollub.modefactory.posestrategy;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class KeyActionStrategy {
    private int keyCode;

    public abstract int pressKey();

    public abstract int pressMouseKey();

}