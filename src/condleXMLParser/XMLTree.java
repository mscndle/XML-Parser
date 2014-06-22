package condleXMLParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

/**
 *  Represents an xml file as a tree with non-leaf nodes representing attributes
 *  and leaf nodes representing data
 */
public class XMLTree {
    protected Node root;

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

    // POSSIBLE API calls to add
    /*
    String getCurrentAttribute() {} //implemented in Iterator
    String getAllSiblings() {}   //if on the last sibling, return
    String getParentAttribute() {}
    String getChildAttribute() {} //should return null if no more child attributes exist
    String getDFSRepresentation() {}
    String getData() {} //can only be run on an attribute which has a data child (leafnode)
     */

    /**
     * Constructor sets up parser and creates XMLTree object
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
    }

    /**
     * @return  String value of the root node
     */
    public String getRootNode() {
        return root.data;
    }

    public Iterator getIterator() {
        return new Iterator() {
            @Override
            public boolean hasNext() {  //refers to attribute siblings
                return false;
            }

            @Override
            public String next() {  //return the current sibling object
                return null;
            }

            @Override
            public void remove() { }  //not implemented

            public List<String> getAllSiblings() {

                return null;
            }

        };
    }



}
