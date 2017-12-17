package com.ferzerkerx.treeinspector.controller;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import com.ferzerkerx.treeinspector.model.TreeData;
import com.ferzerkerx.treeinspector.service.TreeDataService;
import com.ferzerkerx.treeinspector.ui_control.ZoomableScrollPane;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MainController {
    private static final int CIRCLE_DIAMETER = 20;
    private static final int LINE_WIDTH = 3;

    @FXML
    private Slider slider;

    @FXML
    private VBox mainBox;

    @Inject
    private TreeDataService treeDataService;

    private Canvas mainCanvas;

    @FXML
    private void initialize() {
        System.out.println("Initializing...");

        mainCanvas = new Canvas(4000, 4000);
        ZoomableScrollPane zoomableScrollPane = new ZoomableScrollPane(mainCanvas);


        zoomableScrollPane.zoomFactorProperty().bind(slider.valueProperty());
        mainBox.getChildren().add(0, zoomableScrollPane);

        TreeData treeData = treeDataService.getTreeData();
        Map<Integer, TreeData.Node> nodes = treeData.getNodes();
        Map<Integer, List<TreeData.Node>> nodesPerLevel = treeData.getNodesPerLevel();
        calculateDrawingPointForNodes(nodesPerLevel);

        drawTree(nodes);

        System.out.println("Done...");
    }

    private void drawTree(Map<Integer, TreeData.Node> nodes) {
        GraphicsContext gc = mainCanvas.getGraphicsContext2D();
        for (TreeData.Node node : nodes.values()) {
            double x = node.getPoint2D().getX();
            double y = node.getPoint2D().getY();

            Color color = new Color(Math.random(), Math.random(), Math.random(), 1.0);
            gc.setFill(color);

            double offset = CIRCLE_DIAMETER / 2;
            gc.fillOval(x - offset, y - offset, CIRCLE_DIAMETER, CIRCLE_DIAMETER);
            String value = node.getValue();
            gc.fillText(value, x - offset, y - offset);

            if (!node.hasChildren()) {
                continue;
            }
            for (TreeData.Node childNode : node.getChildren()) {
                double childx = childNode.getPoint2D().getX();
                double childy = childNode.getPoint2D().getY();
                gc.setStroke(color);
                gc.setLineWidth(LINE_WIDTH);

                gc.strokeLine( //
                    x, //
                    y, //
                    childx, //
                    childy //
                ); //

            }
        }
    }

    private void calculateDrawingPointForNodes(Map<Integer, List<TreeData.Node>> nodesPerLevel) {

        double verticalDistance = (mainCanvas.getHeight() - CIRCLE_DIAMETER) / nodesPerLevel.size();
        List<TreeData.Node> parentsNodesInLevel = null;

        for (Map.Entry<Integer, List<TreeData.Node>> nodeLevelEntry : nodesPerLevel.entrySet()) {
            int level = nodeLevelEntry.getKey();
            List<TreeData.Node> nodesInLevel = nodeLevelEntry.getValue();
            double horizontalDistance = (mainCanvas.getWidth() - CIRCLE_DIAMETER) / (nodesInLevel.size() + 1);

            if (parentsNodesInLevel != null) {
                sortNodesForRendering(parentsNodesInLevel, nodesInLevel);
            }
            int counter = 1;
            for (TreeData.Node node : nodesInLevel) {
                double x = counter * horizontalDistance;
                double y = level * verticalDistance;
                node.setPoint2D(new Point2D(x, y));
                counter++;
            }
            parentsNodesInLevel = nodesInLevel;
        }
    }

    private void sortNodesForRendering(final List<TreeData.Node> finalParentsNodesInLevel, List<TreeData.Node> nodesInLevel) {
        nodesInLevel.sort((o1, o2) -> {
            TreeData.Node node1 = o1.getParent();
            TreeData.Node node2 = o2.getParent();

            Integer indexNode1 = finalParentsNodesInLevel.indexOf(node1);
            Integer indexNode2 = finalParentsNodesInLevel.indexOf(node2);

            return indexNode1.compareTo(indexNode2);
        });
    }
}
