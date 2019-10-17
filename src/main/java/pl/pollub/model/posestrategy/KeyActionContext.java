package pl.pollub.model.posestrategy;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class KeyActionContext {
    private KeyActionStrategy strategy;

    public void set(KeyActionStrategy strategy) {
        this.strategy = strategy;
    }

    public int doAction() {
        return this.strategy.pressKey();
    }
}