package pl.pollub.model.fasade;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import com.thalmic.myo.exception.HubNotFoundException;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import pl.pollub.factory.collectors.AbstractDataCollector;
import pl.pollub.model.MyoState;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Device {
    private final ObjectProperty<Hub> hub;
    private final ObjectProperty<Myo> myo;
    private final ObjectProperty<MyoState> myoState;
    private final StringProperty communicate;

    private final List<AbstractDataCollector> dataCollectors;

    public Device(ObjectProperty<Hub> hub, ObjectProperty<Myo> myo, ObjectProperty<MyoState> myoState, StringProperty communicate, List<AbstractDataCollector> dataCollectors) {
        this.hub = hub;
        this.myo = myo;
        this.myoState = myoState;
        this.communicate = communicate;
        this.dataCollectors = dataCollectors;
    }

    public void addDataCollector(AbstractDataCollector dataCollector) throws HubNotFoundException {
        if(hub.get() == null){
            throw new HubNotFoundException();
        }

        communicate.set("Dodaję obserwatora: " +dataCollector.getClass());
        dataCollector.getProperties().getIsActive().set(true);
        hub.get().addListener(dataCollector);
        dataCollectors.add(dataCollector);
    }

    public void removeDataCollector(AbstractDataCollector dataCollector) throws HubNotFoundException {
        if(hub.get() == null){
            throw new HubNotFoundException();
        }

        System.out.println("Usuwam obserwatora: " +dataCollector.getClass());
        dataCollector.getProperties().getIsActive().set(false);
        hub.get().removeListener(dataCollector);
        dataCollectors.remove(dataCollector);
    }

    public void requestBattery(){
        myo.get().requestBatteryLevel();
    }

    public  static Device create(){
        return new Device(
                new SimpleObjectProperty<>(),
                new SimpleObjectProperty<>(),
                new SimpleObjectProperty<>(MyoState.MYO_UNKNOWN),
                new SimpleStringProperty(),
                new ArrayList<>()
        );
    }
}

