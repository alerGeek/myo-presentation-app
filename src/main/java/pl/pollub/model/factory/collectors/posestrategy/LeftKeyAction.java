package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;
import pl.pollub.tool.RobotManager;

import static java.awt.event.KeyEvent.VK_LEFT;

@Getter
@Setter
public class LeftKeyAction extends KeyActionStrategy {

    public LeftKeyAction() {
        super(VK_LEFT);
    }

    public int pressKey() {
        return super.pressKey();
    }
}