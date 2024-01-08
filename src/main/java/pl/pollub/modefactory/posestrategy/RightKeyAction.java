package pl.pollub.modefactory.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.util.RobotManager;

import java.awt.event.KeyEvent;

@Setter
@Getter
public class RightKeyAction extends KeyActionStrategy {

    private int keyCode;

    public RightKeyAction() {
        keyCode = KeyEvent.VK_RIGHT;
    }

    @Override
    public int pressKey() {
        return RobotManager.simulateKeyEvent(keyCode);
    }

    @Override
    public int pressMouseKey() {
        throw new IllegalArgumentException();
    }
}