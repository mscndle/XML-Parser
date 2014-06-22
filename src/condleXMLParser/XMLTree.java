package condleXMLParser;

import java.io.IOException;
import java.util.*;

/**
 *  Represents an xml file as a tree with non-leaf nodes representing xml elements
 *  and leaf nodes representing values
 */
public class XMLTree {
    protected Node root;
    private LinkedList<Node> dfsQueue;

    static class Node {
        String data;
        ArrayList<Node> childNodes;
        HashMap<String, String> attributes; //optional

        Node(String data) {
            this.data = data;
            this.childNodes = new ArrayList<Node>();
        }

        void addChild(Node node) {
            this.childNodes.add(node);
        }
    }

    /**
     * Constructor sets up parser and creates XMLTree object
     * It also creates the DFS queue which is later used by the iterator
     *
     * @param pathToFile    String path to xml file
     */
    public XMLTree(String pathToFile) {
        XMLParser parser = new XMLParser(pathToFile);
        try {
            String xml = parser.readXMLFile(pathToFile);
            parser.parseXMLToTree(xml, this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.dfsQueue = new LinkedList<Node>();
        this.dfsTreeTraversal(this.root, this.dfsQueue);
    }

    /**
     * @return  String value of the root node
     */
    public Node getRootNode() {
        return root;
    }

    public Iterator getIterator() {
        return new Iterator() {
            LinkedList<Node> queue = new LinkedList<Node>();
            int currentIndex;
            Node currentNode;

            {
                this.currentIndex = 0;
                this.currentNode = XMLTree.this.getRootNode();
                this.queue = XMLTree.this.getDfsNodeQueue();
            }

            @Override
            public boolean hasNext() {
                return (this.currentIndex < this.queue.size());
            }

            @Override
            public String next() {
                this.currentNode = this.queue.get(this.currentIndex++);
                return this.currentNode.data;
            }

            @Override
            public void remove() { }  //not implemented

            /**
             * @return  Data of this XML element, invalid if curr node is not a leaf
             */
            public String getXMLData() {
                if (this.currentNode.childNodes == null) {
                    return this.currentNode.data;
                }

                throw new IllegalStateException("Current node is an XML element");
            }

            /**
             * @return  Child nodes of the current node during iteration
             */
            public List<Node> getAllChildNodes() {
                if (this.currentNode.childNodes != null) {
                    return this.currentNode.childNodes;
                }

                throw new IllegalArgumentException("Current node is a leaf node");
            }
        };
    }

    /**
     * @return  DFS queue of nodes in the XMLTree
     */
    public LinkedList<Node> getDfsNodeQueue() {
        //this.dfsTreeTraversal(this.root, dfsQueue);
        return this.dfsQueue;
    }

    /**
     * Called by the constructor to create a queue of all nodes
     * in the tree using DFS
     *
     * @param node  Current node in DFS
     * @param queue Represents DFS state of the tree
     */
    private void dfsTreeTraversal(Node node, LinkedList<Node> queue) {
        //System.out.println("called");
        if (node != null) {
            queue.add(node);    //Append to the DFS list

            ArrayList<Node> childNodes = node.childNodes;

            if (childNodes != null) {
                for (Node temp : childNodes) {
                    this.dfsTreeTraversal(temp, queue);
                }
            }
        }
    }

    /**
     * Prints out the data values of each XMLTree node
     */
    public void printDfsTraversal() {
        for (Node temp: this.dfsQueue) {
            System.out.println(temp.data + " ");
        }
    }



}
