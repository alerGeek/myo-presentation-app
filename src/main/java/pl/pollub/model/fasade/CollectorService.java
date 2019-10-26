package pl.pollub.model.fasade;

import javafx.beans.property.StringProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import lombok.AllArgsConstructor;
import pl.pollub.exception.HubNotFoundException;
import pl.pollub.exception.MyoNotFoundException;

@AllArgsConstructor
public class CollectorService extends ScheduledService<Void> {
    private final Device device;
    private final StringProperty communicate;

    protected Task<Void> createTask() {
        setMaximumFailureCount(1);
        return new Task<Void>() {
            protected Void call() throws Exception {
                try {
                    if (device.getHub().get() == null) {
                        throw new HubNotFoundException();
                    }

                    if (device.getMyo().get() == null) {
                        throw new MyoNotFoundException();
                    }

                    device.getHub().getValue().run(1000/20);
                    device.requestBattery();
                } catch (Exception ex) {
                    communicate.set(ex.getMessage());
                    CollectorService.this.failed();
                }
                return null;
            }
        };
    }


    public void restart() {
        super.restart();
    }

    protected void failed() {
        super.failed();
    }
}