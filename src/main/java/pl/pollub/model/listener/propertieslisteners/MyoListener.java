package pl.pollub.model.listener.propertieslisteners;

import com.thalmic.myo.Myo;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ToggleButton;
import pl.pollub.exception.MyoNotFoundException;

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

    public void changed(ObservableValue<? extends Myo> observable, Myo oldValue, Myo newValue) throws MyoNotFoundException {
//        Platform.runLater(() -> {
//            if (newValue == null) {
//                onNull();
//            } else {
//                onConnected();
//            }
//        });
    }

    private void onConnected() {
//        this.allButtons.forEach(button -> button.setDisable(false));
//        this.listenersButtons.forEach(button -> button.setSelected(false));
//        this.addMyoButton.setSelected(true);
//        FacadeWrapperSingleton.INSTANCE.getFacade().getDevice().getDataCollectors().stream()
//                .filter(listener -> listener.getProperties().getIsActive().get())
//                .forEach(FacadeWrapperSingleton.INSTANCE.getFacade().getDevice()::addDataCollector);
//        this.allButtons.forEach(button -> {
//            button.setSelected(true);
//            button.setDisable(true);
//        });

//        FacadeWrapperSingleton.INSTANCE.getFacade().collectData();
    }

    private void onNull() {
//        FacadeWrapperSingleton.INSTANCE.getFacade().getDevice().getDataCollectors().stream()
//                .filter(listener -> listener.getProperties().getIsActive().get())
//                .forEach(FacadeWrapperSingleton.INSTANCE.getFacade().getDevice()::removeDataCollector);
//        this.allButtons.forEach(button -> {
//            button.setSelected(true);
//            button.setDisable(true);
//        });
//        this.addMyoButton.setDisable(false);
//        FacadeWrapperSingleton.INSTANCE.getFacade().stopCollectData();
//        throw new MyoNotFoundException("Myo nie zostało znalezione");
    }
}