package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.MouseEvent;

@Getter
@Setter
public class MouseRightClickAction extends KeyActionStrategy {

    public MouseRightClickAction() {
        super(MouseEvent.BUTTON2_DOWN_MASK);
    }


    @Override
    public int pressMouseKey() {
        return super.pressMouseKey();
    }
}
