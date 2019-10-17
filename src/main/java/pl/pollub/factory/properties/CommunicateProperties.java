package pl.pollub.factory.properties;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommunicateProperties extends AbstractProperties {
    private final StringProperty allCommunicate;
    private final StringProperty newCommunicate;

    @Builder
    public CommunicateProperties(BooleanProperty isActive, StringProperty allCommunicate, StringProperty newCommunicate) {
        super(isActive);
        this.allCommunicate = allCommunicate;
        this.newCommunicate = newCommunicate;

        appendCommunicateOnUpdate();
    }

    public void appendCommunicateOnUpdate() {
        this.newCommunicate.addListener((observable, oldValue, newValue) -> {
            if (allCommunicate.getValue() != null && !allCommunicate.getValue().equals("")) {
                allCommunicate.setValue(allCommunicate.getValue() + "\n");
            }

            allCommunicate.setValue(allCommunicate.getValue() + newValue);
            Platform.runLater(() -> {
            });
        });
    }

    public static CommunicateProperties createProperties() {
        return CommunicateProperties.builder()
                .allCommunicate(new SimpleStringProperty())
                .newCommunicate(new SimpleStringProperty())
                .isActive(new SimpleBooleanProperty())
                .build();
    }
}
