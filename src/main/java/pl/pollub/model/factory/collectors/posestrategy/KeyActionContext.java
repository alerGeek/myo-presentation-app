package pl.pollub.model.factory.collectors.posestrategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
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