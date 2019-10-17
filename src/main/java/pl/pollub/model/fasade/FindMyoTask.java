package pl.pollub.model.fasade;

import com.thalmic.myo.Hub;
import com.thalmic.myo.Myo;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import pl.pollub.exception.MyoNotFoundException;
import pl.pollub.model.MyoState;
import pl.pollub.tool.FacadeWrapperSingleton;

@AllArgsConstructor
public class FindMyoTask extends Task<Void> {
    private static final int SEARCHING_TIME = 3000;
    private final Device device;
    private final ObjectProperty<MyoState> connectState;
    private final StringProperty communicate;

    public FindMyoTask(Device device) {
        this.device = device;
        this.connectState = device.getMyoState();
        this.communicate = device.getCommunicate();
    }

    protected Void call() throws Exception {
        connectState.setValue(MyoState.MYO_SEARCHING);
        communicate.setValue(connectState.getValue().getCommunicateText());
        try {
            addHub("pl.pollub.myo-presentation-app");
            addMyo();
        } catch (Exception ex) {
            connectState.setValue(MyoState.MYO_NOT_FOUND);
            communicate.setValue(connectState.getValue().getCommunicateText());
            communicate.setValue(ex.toString());
            ex.printStackTrace();
            failed();
        }
        return null;
    }


    protected void failed() {
        super.failed();
        cancel();
    }

    private void addMyo() {
        ObjectProperty<Myo> myoProperty = device.getMyo();
        ObjectProperty<Hub> hubProperty = device.getHub();

        myoProperty.setValue(hubProperty.getValue().waitForMyo(SEARCHING_TIME));

        if (myoProperty.getValue() != null) {
            connectState.setValue(MyoState.MYO_FOUND);
        } else {
            connectState.setValue(MyoState.MYO_NOT_FOUND);
            throw new MyoNotFoundException("Myo nie zostało znalezione.");
        }
        communicate.setValue(connectState.getValue().getCommunicateText());
    }

    private void addHub(String applicationName) {
        ObjectProperty<Hub> hubProperty = device.getHub();

        if (hubProperty.getValue() == null)
            hubProperty.setValue(new Hub(applicationName));
    }
}