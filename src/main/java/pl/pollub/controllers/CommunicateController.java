package pl.pollub.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import pl.pollub.model.factory.collectors.AbstractDataCollector;
import pl.pollub.model.fasade.DeviceFacade;
import pl.pollub.tool.FacadeWrapperSingleton;

import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class CommunicateController implements Initializable {
    @FXML private TextArea communicateArea;
    @FXML private AnchorPane paneApp;
    @FXML private TableColumn<AbstractDataCollector, String> modeCol;
    @FXML private TableColumn<AbstractDataCollector, String> isActiveCol;
    @FXML private TableView<AbstractDataCollector> listView;
    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getFacade();

    public void initialize(URL location, ResourceBundle resources) {
        setTableData();
        updateTableData().start();
    }

    private void setTableData() {
        listView.setItems(deviceFacade.getDevice().getDataCollectors());
        modeCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getCollectorName()));
        isActiveCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getProperties().getIsActive().getValue().toString()));
    }

    private ScheduledService<Void> updateTableData() {
        return new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        if (!listView.getItems().isEmpty()) {
                            listView.refresh();
                        }
                        return null;
                    }
                };
            }
        };
    }
}