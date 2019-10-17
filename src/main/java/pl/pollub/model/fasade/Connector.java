package pl.pollub.model.fasade;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Connector {
    private final Device device;
    private FindMyoTask findMyoTask;

    public void connectMyo() {
        if (this.findMyoTask.isDone()) {
            this.findMyoTask = new FindMyoTask(device);
        }

        Thread thread = new Thread(this.findMyoTask);

        thread.setName("myoThread");
        thread.setDaemon(true);
        thread.start();
    }
}