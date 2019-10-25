package pl.pollub.model.fasade;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.exception.HubNotFoundException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import pl.pollub.model.MyoState;
import pl.pollub.model.factory.collectors.AbstractDataCollector;

@Getter
public class Device {
    private final ObjectProperty<Hub> hub;
    private final ObjectProperty<Myo> myo;
    private final ObjectProperty<MyoState> myoState;
    private final StringProperty communicate;

    private final ObservableList<AbstractDataCollector> dataCollectors;

    public Device(ObjectProperty<Hub> hub, ObjectProperty<Myo> myo, ObjectProperty<MyoState> myoState, StringProperty communicate, ObservableList<AbstractDataCollector> dataCollectors) {
        this.hub = hub;
        this.myo = myo;
        this.myoState = myoState;
        this.communicate = communicate;
        this.dataCollectors = dataCollectors;
    }

    public void addDataCollector(AbstractDataCollector dataCollector) throws HubNotFoundException {
        if (hub.get() == null) {
            throw new HubNotFoundException();
        }

        if (!dataCollectors.contains(dataCollector)) {
            dataCollectors.add(dataCollector);
        }

        hub.get().addListener(dataCollector);
        dataCollector.getProperties().getIsActive().set(true);
    }

    public void removeDataCollector(AbstractDataCollector dataCollector) {
        if (hub.get() == null) {
            try {
                throw new HubNotFoundException();
            } catch (HubNotFoundException e) {
                communicate.setValue(e.getMessage());
            }
        }

        hub.get().removeListener(dataCollector);
        dataCollector.getProperties().getIsActive().set(false);
    }

    public void removeAllDataCollectors() {
        if (hub.get() == null) {
            try {
                throw new HubNotFoundException();
            } catch (HubNotFoundException e) {
                communicate.setValue(e.getMessage());
            }
        }

        dataCollectors.forEach(dataCollector -> {
            hub.get().removeListener(dataCollector);
            dataCollector.getProperties().getIsActive().set(false);
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
                FXCollections.observableArrayList()
        );
    }
}

