package pl.pollub.model.devicepropertieslisteners;

import com.thalmic.myo.Myo;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.util.List;

public class MyoListener extends Object implements ChangeListener<Myo> {
    private final List<ToggleButton> allButtons;
    private final List<ToggleButton> listenersButtons;
    private final ToggleButton addMyoButton;

    public MyoListener(List<ToggleButton> allButtons, List<ToggleButton> listenersButtons, ToggleButton addMyoButton) {
        this.allButtons = allButtons;
        this.listenersButtons = listenersButtons;
        this.addMyoButton = addMyoButton;
    }

    public void changed(ObservableValue<? extends Myo> observable, Myo oldValue, Myo newValue) {
        Platform.runLater(() -> {
            if (newValue == null) {
                onNull();
            } else {
                onConnected();
            }
        });
    }

    private void onConnected() {
        this.allButtons.forEach(button -> {
            button.setSelected(false);
            button.setDisable(false);
        });
        this.addMyoButton.setSelected(true);
        this.addMyoButton.setDisable(true);
    }

    private void onNull() {
        FacadeWrapperSingleton.INSTANCE.getFacade().getDevice().removeAllDataCollectors();

        this.allButtons.forEach(button -> {
            button.setSelected(true);
            button.setDisable(true);
        });

        this.addMyoButton.setDisable(false);
        FacadeWrapperSingleton.INSTANCE.getFacade().stopCollectData();
    }
}