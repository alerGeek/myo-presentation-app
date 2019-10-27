package pl.pollub.device;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import javafx.beans.property.ObjectProperty;
import javafx.concurrent.Task;
import lombok.Getter;
import pl.pollub.exception.MyoConnectNotFoundException;
import pl.pollub.exception.MyoNotFoundException;
import pl.pollub.types.MyoState;

@Getter
public class Connector {
    private static final int SEARCHING_TIME = 3000;
    private static final String APP_ID = "pl.pollub.presenter-myo";
    private final Device device;
    private Task<Void> findMyoTask;

    public Connector(Device device) {
        this.device = device;
        this.findMyoTask = createFindMyoTask();
    }

    public void connectMyo() {
        if (this.findMyoTask.isDone()) {
            this.findMyoTask = createFindMyoTask();
        }

        Thread thread = new Thread(this.findMyoTask);

        thread.setName("myoThread");
        thread.setDaemon(true);
        thread.start();
    }

    public Task<Void> createFindMyoTask() {
        return new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                device.getMyoState().setValue(MyoState.MYO_SEARCHING);
                try {
                    ObjectProperty<Hub> hubProperty = device.getHub();
                    ObjectProperty<Myo> myoProperty = device.getMyo();

                    if (hubProperty.getValue() == null) {
                        hubProperty.setValue(new Hub(APP_ID));
                    }

                    myoProperty.setValue(hubProperty.getValue().waitForMyo(SEARCHING_TIME));

                    if (myoProperty.getValue() == null) {
                        device.getMyoState().setValue(MyoState.MYO_NOT_FOUND);
                        throw new MyoNotFoundException();
                    }
                    device.getMyoState().setValue(MyoState.MYO_FOUND);
                } catch (Exception ex) {

                    device.getMyoState().setValue(MyoState.MYO_NOT_FOUND);
                    if (ex.getMessage().equals("Unable to connect to Myo Connect. Is Myo Connect running?")) {
                        ex = new MyoConnectNotFoundException("Nie można połączyć z Myo Connect. Sprawdź czy Myo Connect działa i uruchom ponownie aplikację.");
                    }
                    device.getCommunicate().setValue(ex.toString());
                    ex.printStackTrace();
                }
                return null;
            }
        };
    }
}

