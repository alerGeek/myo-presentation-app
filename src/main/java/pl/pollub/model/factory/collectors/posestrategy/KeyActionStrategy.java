package pl.pollub.model.factory.collectors.posestrategy;

import pl.pollub.tool.RobotManager;

public abstract class KeyActionStrategy {
    private final int keyCode;

    public KeyActionStrategy(int keyCode) {
        this.keyCode = keyCode;
    }

    public int pressKey() {
        return RobotManager.simulateKeyEvent(keyCode);
    }

    public int pressMouseKey() {
        RobotManager.simulateMouseButtonEvent(keyCode);
        return keyCode;
    }

}