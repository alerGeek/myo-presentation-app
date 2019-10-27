package pl.pollub.device.propertylistener;

import com.thalmic.myo.Myo;
import com.thalmic.myo.exception.HubNotFoundException;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import pl.pollub.util.FacadeWrapperSingleton;

import java.util.List;

public class MyoListener implements ChangeListener<Myo> {
    private final List<ToggleButton> allButtons;
    private final ToggleButton addMyoButton;

    public MyoListener(List<ToggleButton> allButtons, ToggleButton addMyoButton) {
        this.allButtons = allButtons;
        this.addMyoButton = addMyoButton;
    }

    public void changed(ObservableValue<? extends Myo> observable, Myo oldValue, Myo newValue) {
        Platform.runLater(() -> {
            if (newValue == null) {
                onNull();
            }
        });
    }

    private void onNull() {
        try {
            FacadeWrapperSingleton.INSTANCE.getDeviceFacade().getDevice().removeAllDataCollectors();
        } catch (HubNotFoundException e) {
            e.printStackTrace();
        }

        this.allButtons.forEach(button -> {
            button.setSelected(true);
            button.setDisable(true);
        });

        this.addMyoButton.setDisable(false);
        FacadeWrapperSingleton.INSTANCE.getDeviceFacade().stopCollectData();
    }
}