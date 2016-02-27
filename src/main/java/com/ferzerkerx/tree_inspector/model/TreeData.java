package com.ferzerkerx.tree_inspector.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.geometry.Point2D;

public class TreeData {
    private Map<Integer, Node> nodes;
    private Map<Integer, List<Node>> nodesPerLevel;

    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    public void setNodes(Map<Integer, Node> nodes) {
        this.nodes = nodes;
    }

    public Map<Integer, List<Node>> getNodesPerLevel() {
        return nodesPerLevel;
    }

    public void setNodesPerLevel(Map<Integer, List<Node>> nodesPerLevel) {
        this.nodesPerLevel = nodesPerLevel;
    }

    public static class Node {
        private final List<Node> children;
        private String value;
        private Point2D point2D;
        private Node parent;
        private int level;

        public Node(String value) {
            this.value = value;
            children = new ArrayList<>();
            parent = null;
            level = -1;
        }

        public Point2D getPoint2D() {
            return point2D;
        }

        public void setPoint2D(Point2D point2D) {
            this.point2D = point2D;
        }

        public String getValue() {
            return value;
        }

        public List<Node> getChildren() {
            return children;
        }

        public Node getParent() {
            return parent;
        }

        public boolean hasChildren() {
            return !children.isEmpty();
        }

        public boolean hasParent() {
            return parent != null;
        }

        public void addChild(Node node) {
            children.add(node);
            node.setParent(this);
        }

        public void setParent(Node node) {
            this.parent = node;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Node node = (Node) o;

            return value != null ? value.equals(node.value) : node.value == null;
        }

        @Override
        public int hashCode() {
            return value != null ? value.hashCode() : 0;
        }
    }
}
