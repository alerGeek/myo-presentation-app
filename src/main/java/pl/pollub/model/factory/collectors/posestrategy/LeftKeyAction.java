package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.tool.RobotManager;

import static java.awt.event.KeyEvent.VK_LEFT;

@Getter
@Setter
public class LeftKeyAction extends KeyActionStrategy {
    private int keyCode;

    public LeftKeyAction() {
        this.keyCode = VK_LEFT;
    }

    public int pressKey() {
        return RobotManager.simulateKeyEvent(VK_LEFT);
    }

    @Override
    int pressMouseKey() {
        throw new IllegalArgumentException();
    }
}