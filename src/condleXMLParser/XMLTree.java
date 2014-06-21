package condleXMLParser;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class XMLTree {
    Node root;

    static class Node {
        String data;
        ArrayList<Node> childNodes;

        Node(String data) {
            this.data = data;
            this.childNodes = new ArrayList<Node>();
        }

        void addChild(Node node) {
            this.childNodes.add(node);
        }
    }

    /**
     * Level order traversal of the XMLTree
     * @return  String formatted for level order traversal
     */
    String getTree() {
        StringBuilder tree = new StringBuilder();
        Node node = root;

        List<Node> mainQueue = new LinkedList<Node>();
        List<Node> childQueue = new LinkedList<Node>();
        mainQueue.add(node);

        while (!mainQueue.isEmpty()) {  //Use BFS to print out the tree
            node = mainQueue.remove(0);
            tree.append(node.data + " ");

            if (childQueue.isEmpty()) { //one level is up
                tree.append("\n");
                for (Node child: node.childNodes) {
//                    System.out.println("adding child nodes");
                    childQueue.add(child);
                }
            } else {
                mainQueue.add(childQueue.remove(0));
            }

            if (!childQueue.isEmpty()) {    mainQueue.add(childQueue.remove(0));    }
        }

        return tree.toString();
    }


    /**
     * DFS of the tree
     */
    public String getDFSTree() {
        StringBuilder treeStr = new StringBuilder();
        Stack<Node> nodeStack = new Stack<Node>();
        nodeStack.push(this.root);

        //String tree = getDFSTree(this.root, treeStr).toString();
        getDFSTree(this.root, treeStr);
        return treeStr.toString();
    }

    private void getDFSTree(Node node, StringBuilder treeStr) {
        //Node node = nodeStack.peek();
        System.out.println("entered recursive function");

        for (Node child: node.childNodes) {
            if (child.childNodes == null) {
                treeStr.append(child.data);
                System.out.println("reached leaf");
            } else {
                System.out.println("child has children, calling function again");
                getDFSTree(child, treeStr);
            }
        }

        //return treeStr;
    }












}
