package pl.pollub.modefactory.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.util.RobotManager;

import java.awt.event.MouseEvent;

@Getter
@Setter
public class MouseRightClickAction extends KeyActionStrategy {
    private int keyCode;

    public MouseRightClickAction() {
        this.keyCode = MouseEvent.BUTTON2_DOWN_MASK;
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
