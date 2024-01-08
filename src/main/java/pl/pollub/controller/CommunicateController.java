package pl.pollub.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.MapChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
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
import pl.pollub.device.DeviceFacade;
import pl.pollub.modefactory.AbstractMode;
import pl.pollub.util.FacadeWrapperSingleton;

import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;

@Getter
@Setter
public class CommunicateController implements Initializable {
    @FXML private TextArea communicateArea;
    @FXML private AnchorPane paneApp;

    @FXML private TableView<Map.Entry<String, AbstractMode>> listView;
    @FXML private TableColumn<Map.Entry<String, AbstractMode>, String> modeCol;
    @FXML private TableColumn<Map.Entry<String, AbstractMode>, String> isActiveCol;

    private final DeviceFacade deviceFacade = FacadeWrapperSingleton.INSTANCE.getDeviceFacade();
    private ObservableList<Map.Entry<String, AbstractMode>> entries;

    public void initialize(URL location, ResourceBundle resources) {
        ObservableMap<String, AbstractMode> modesMap = deviceFacade.getDevice().getModesMap();

        modesMap.addListener((MapChangeListener<? super String, ? super AbstractMode>) change -> {
            entries = FXCollections.observableArrayList(modesMap.entrySet());
            listView.setItems(entries);
        });

        modeCol.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().getKey()));
        isActiveCol.setCellValueFactory(param -> new SimpleStringProperty(
                param.getValue().getValue().getProperties().getIsActive().getValue() ? "aktywny" : "nieaktywny"));

        listView.getColumns().setAll(Arrays.asList(modeCol, isActiveCol));

        updateTableData().start();
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