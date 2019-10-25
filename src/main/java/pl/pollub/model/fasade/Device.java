package pl.pollub.model.fasade;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.exception.HubNotFoundException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
import lombok.Getter;
import pl.pollub.model.MyoState;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.factory.modes.AbstractMode;

@Getter
public class Device {
    private final ObjectProperty<Hub> hub;
    private final ObjectProperty<Myo> myo;
    private final ObjectProperty<MyoState> myoState;
    private final StringProperty communicate;

    private final ObservableMap<String, AbstractMode> modesMap;

    public Device(ObjectProperty<Hub> hub, ObjectProperty<Myo> myo, ObjectProperty<MyoState> myoState, StringProperty communicate, ObservableMap<String, AbstractMode> modesMap) {
        this.hub = hub;
        this.myo = myo;
        this.myoState = myoState;
        this.communicate = communicate;
        this.modesMap = modesMap;
    }

    public void addDataCollector(AbstractDataCollector dataCollector) throws HubNotFoundException {
        if (hub.get() == null) {
            throw new HubNotFoundException();
        }

        dataCollector.getProperties().getIsActive().setValue(true);
        hub.getValue().addListener(dataCollector);
    }

    public void removeDataCollector(AbstractDataCollector dataCollector) throws HubNotFoundException {
        if (hub.get() == null) {
            throw new HubNotFoundException();
        }

        final AbstractMode abstractMode = modesMap.get(dataCollector.getCollectorName());
        abstractMode.getProperties().getIsActive().set(false);
        hub.getValue().removeListener(dataCollector);
    }

    public void removeAllDataCollectors() {
        if (hub.get() == null) {
            try {
                throw new HubNotFoundException();
            } catch (HubNotFoundException e) {
                communicate.setValue(e.getMessage());
            }
        }

        modesMap.forEach((key, abstractMode) -> {
            hub.get().removeListener(abstractMode.getDataCollector());
            abstractMode.getProperties().getIsActive().set(false);
        });
    }

    public void requestBattery() {
        myo.get().requestBatteryLevel();
    }

    public static Device create() {
        return new Device(
                new SimpleObjectProperty<>(),
                new SimpleObjectProperty<>(),
                new SimpleObjectProperty<>(MyoState.MYO_UNKNOWN),
                new SimpleStringProperty(),
                FXCollections.observableHashMap());
    }
}

