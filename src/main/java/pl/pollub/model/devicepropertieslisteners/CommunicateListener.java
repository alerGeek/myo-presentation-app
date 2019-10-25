package pl.pollub.model.devicepropertieslisteners;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextArea;

public class CommunicateListener implements ChangeListener<String> {
    private final TextArea communicateArea;

    public CommunicateListener(TextArea communicateArea) {
        this.communicateArea = communicateArea;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        Platform.runLater(() -> {
            if (!(oldValue == null) && !(oldValue.equals(""))) {
                communicateArea.appendText("\n");
            }
            communicateArea.appendText(newValue);
        });
    }
}