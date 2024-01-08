package pl.pollub.modefactory.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.util.RobotManager;

import static java.awt.event.KeyEvent.VK_LEFT;

@Getter
@Setter
public class LeftKeyAction extends KeyActionStrategy {
    private int keyCode;

    public LeftKeyAction() {
        this.keyCode = VK_LEFT;
    }

    public int pressKey() {
        return RobotManager.simulateKeyEvent(keyCode);
    }

    @Override
    public int pressMouseKey() {
        throw new IllegalArgumentException();
    }
}