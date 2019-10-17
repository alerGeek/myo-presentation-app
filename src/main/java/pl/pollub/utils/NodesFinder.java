package pl.pollub.utils;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class NodesFinder {
    public static ArrayList<ToggleButton> getAllToggleButtons(List<Node> allNodes) {
        return (ArrayList<ToggleButton>) allNodes.stream()
                .filter(node -> node instanceof ToggleButton)
                .map(node -> (ToggleButton) node)
                .collect(Collectors.toList());
    }

    public static ArrayList<ToggleButton> getListenersButtons(List<ToggleButton> allToggleButtons) {
        return (ArrayList<ToggleButton>) allToggleButtons.stream()
                .filter(button -> button.getId().equals("addListenerButton"))
                .collect(Collectors.toList());
    }


    public static ArrayList<Node> getAllNodes(Parent root) {
        ArrayList<Node> nodes = new ArrayList<>();
        addAllDescendents(root, nodes);
        return nodes;
    }

    private static void addAllDescendents(Parent parent, ArrayList<Node> nodes) {
        ObservableList<Node> childrenUnmodifiable = parent.getChildrenUnmodifiable();
        for (Node node : childrenUnmodifiable) {
            nodes.add(node);
            if (node instanceof TabPane) {
                addAllDescendentsFromTabPane(nodes, (TabPane) node);
            }
            if (node instanceof Parent) {
                addAllDescendents((Parent) node, nodes);
            }
        }
    }


    private static void addAllDescendentsFromTabPane(ArrayList<Node> nodes, TabPane tabPane) {
        List<Node> tabPaneNodes = (List<Node>) tabPane.getTabs().stream().map(Tab::getContent).collect(Collectors.toList());
        for (Node node : tabPaneNodes)
            addAllDescendents((Parent) node, nodes);
    }
}