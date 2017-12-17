package com.ferzerkerx.treeinspector.ui_control;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.transform.Scale;

public class ZoomableScrollPane extends ScrollPane {

    private DoubleProperty zoomFactor = new SimpleDoubleProperty(1);

    public ZoomableScrollPane(Node content) {

        Group contentGroup = new Group();
        Group zoomGroup = new Group();
        contentGroup.getChildren().add(zoomGroup);
        zoomGroup.getChildren().add(content);
        setContent(contentGroup);
        Scale scale = new Scale(1, 1);
        zoomGroup.getTransforms().add(scale);

        zoomFactor.addListener((observable, oldValue, newValue) -> {
            scale.setX(newValue.doubleValue());
            scale.setY(newValue.doubleValue());
            requestLayout();
        });
    }

    public final DoubleProperty zoomFactorProperty() {
        return zoomFactor;
    }
}
