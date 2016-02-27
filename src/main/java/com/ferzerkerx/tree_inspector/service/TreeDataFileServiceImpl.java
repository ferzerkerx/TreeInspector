package com.ferzerkerx.tree_inspector.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.ferzerkerx.tree_inspector.model.TreeData;
import com.ferzerkerx.tree_inspector.model.TreeInspectorException;

public class TreeDataFileServiceImpl implements TreeDataService {

    @Override
    public TreeData getTreeData() {

        Map<Integer, TreeData.Node> nodes = populateNodeMap();
        Map<Integer, List<TreeData.Node>> nodesPerLevel = populateNodeLevel(nodes);

        TreeData treeData = new TreeData();
        treeData.setNodes(nodes);
        treeData.setNodesPerLevel(nodesPerLevel);
        return treeData;
    }

    private Map<Integer, TreeData.Node> populateNodeMap() {
        InputStream resourceAsStream = getClass().getResourceAsStream("tree_data.txt");
        Scanner in;
        Map<Integer, TreeData.Node> nodes = new LinkedHashMap<>();

        try {
            in = new Scanner(resourceAsStream);
            int n = in.nextInt(); // the number of adjacency relations

            for (int i = 0; i < n; i++) {
                int xi = in.nextInt(); // the ID of a person which is adjacent to yi
                int yi = in.nextInt(); // the ID of a person which is adjacent to xi

                TreeData.Node node1 = nodes.get(xi);
                TreeData.Node node2 = nodes.get(yi);


                if (node1 == null) {
                    node1 = createNode(xi);
                    nodes.put(xi, node1);
                }

                if (node2 == null) {
                    node2 = createNode(yi);
                    nodes.put(yi, node2);
                }

                node1.addChild(node2);
            }
        } catch (Exception e) {
            throw new TreeInspectorException("Couldn't load the data", e);
        }
        return nodes;
    }

    private Map<Integer, List<TreeData.Node>> populateNodeLevel(Map<Integer, TreeData.Node> nodes) {
        Map<Integer, List<TreeData.Node>> nodesPerLevel = new HashMap<>();

        for (TreeData.Node node : nodes.values()) {
            int level = calculateNodeLevel(node);
            node.setLevel(level);
            List<TreeData.Node> nodeList = nodesPerLevel.get(level);
            if (nodeList == null) {
                nodeList = new ArrayList<>();
                nodesPerLevel.put(level, nodeList);
            }
            nodeList.add(node);
        }
        return nodesPerLevel;
    }

    private int calculateNodeLevel(TreeData.Node node) {
        int level = 1;
        TreeData.Node currentNode = node;
        while(currentNode.hasParent()) {
            currentNode = currentNode.getParent();
            level++;
        }

        return level;
    }

    private TreeData.Node createNode(int xi) {
        TreeData.Node node1;
        String value = String.valueOf(xi);
        node1 = new TreeData.Node(value);
        return node1;
    }
}
