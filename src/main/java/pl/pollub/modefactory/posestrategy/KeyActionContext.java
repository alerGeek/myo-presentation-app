package pl.pollub.modefactory.posestrategy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class KeyActionContext {
    private KeyActionStrategy strategy;

    public void set(KeyActionStrategy strategy) {
        this.strategy = strategy;
    }

    public int simulateKeyEvent() {
        return strategy.pressKey();
    }

    public int simulateMouseEvent() {
        return strategy.pressMouseKey();
    }
}