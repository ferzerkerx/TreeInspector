package com.ferzerkerx.tree_inspector.model;



public class TreeInspectorException extends RuntimeException {

    public TreeInspectorException(String message, Exception e) {
        super(message, e);
    }

    public TreeInspectorException(String message) {
        super(message);
    }
}
