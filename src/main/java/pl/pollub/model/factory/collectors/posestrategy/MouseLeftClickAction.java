package pl.pollub.model.factory.collectors.posestrategy;

import lombok.Getter;
import lombok.Setter;

import java.awt.event.MouseEvent;

@Getter
@Setter
public class MouseLeftClickAction extends KeyActionStrategy {

    public MouseLeftClickAction() {
        super(MouseEvent.BUTTON1_DOWN_MASK);
    }

    @Override
    public int pressMouseKey() {
        return super.pressMouseKey();
    }
}