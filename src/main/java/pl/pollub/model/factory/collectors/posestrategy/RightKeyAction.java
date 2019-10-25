package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.tool.RobotManager;

import java.awt.event.KeyEvent;

@Setter
@Getter
public class RightKeyAction extends KeyActionStrategy {

    public RightKeyAction() {
        super(KeyEvent.VK_RIGHT);
    }

    public int pressKey() {
        return super.pressKey();
    }
}