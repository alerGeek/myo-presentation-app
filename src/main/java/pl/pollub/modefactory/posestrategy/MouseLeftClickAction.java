package pl.pollub.modefactory.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.util.RobotManager;

import java.awt.event.MouseEvent;

@Getter
@Setter
public class MouseLeftClickAction extends KeyActionStrategy {
    private int keyCode;

    public MouseLeftClickAction() {
        this.keyCode = MouseEvent.BUTTON1_DOWN_MASK;
    }

    @Override
    public int pressKey() {
        throw new IllegalArgumentException();
    }

    @Override
    public int pressMouseKey() {
        return RobotManager.simulateMouseButtonEvent(keyCode);
    }
}