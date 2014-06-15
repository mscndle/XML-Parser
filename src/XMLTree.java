/**
 * Class that encapsulates the xml tree. Nodes that can have many children.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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

    String getTree() {
        StringBuilder tree = new StringBuilder();
        Node node = root;

        List<Node> queue = new LinkedList<Node>();
        queue.add(node);

        while (!queue.isEmpty()) {  //Use BFS to print out the tree
            node = queue.remove(0);
            tree.append(node.data);

            for (Node child: node.childNodes) {
                queue.add(child);
            }
        }

        return tree.toString();
    }
}
