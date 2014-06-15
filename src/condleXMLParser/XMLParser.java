/**
 * Parses XML file to a XML tree
 */

package condleXMLParser;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.InputMismatchException;

public class XMLParser {


    public XMLParser(String pathToXMLFile) {

    }


    public static void main(String[] args) throws IOException {

        //TODO: Change static xml file input to Stdin or args
        BufferedReader br = new BufferedReader(
                new FileReader("/Users/mscndle/Developer/IdeaProjects/InterviewProb/Coderust/src" +
                        "/sample.xml"));
        String line;

        StringBuilder xml = new StringBuilder();

        while ((line = br.readLine()) != null) {
            xml.append(line);
        }

        System.out.println(parseXMLToTree(xml.toString()).getTree());
    }


    private static void processXMLString(XMLTree.Node parent, String rest) {
        if (parent == null || rest == null) { //mandatory error check
            throw new InputMismatchException("XML not well formed");
        }

        int nodeStart = rest.indexOf('<');    //finding first xml node
        int nodeEnd = rest.indexOf('>');

        if (nodeStart == -1 && nodeEnd == -1) { //no more XML nodes in "rest"
            parent.addChild(new XMLTree.Node(rest));
        }
        else {
            String childNodeData = rest.substring(nodeStart+1, nodeEnd);
            String closingChildNode = "</" + childNodeData + ">";
            int closingChildIndex = rest.indexOf(closingChildNode);

            if (!rest.substring(nodeEnd+1).contains(closingChildNode)) {
                throw new InputMismatchException("XML not well formed");

            } else {

                //process child
                XMLTree.Node childNode = new XMLTree.Node(childNodeData);
                parent.addChild(childNode);
                processXMLString(childNode, rest.substring(nodeEnd+1, closingChildIndex));

                //process sibling
                int siblingStart = closingChildIndex + closingChildNode.length();
                processXMLString(parent, rest.substring(siblingStart));
            }
        }

    }

    private static XMLTree parseXMLToTree(String xml) {

        int rootStart = xml.indexOf('<');    //finding first xml node
        int rootEnd = xml.indexOf('>');

        String rootData = xml.substring(rootStart+1, rootEnd);
        String closingRootNode = "</" + rootData + ">"; //construct possible closing root

        int closingRootIndex = xml.lastIndexOf(closingRootNode); //find index of closingRoot
        if (closingRootIndex == -1) {
            throw new InputMismatchException("XML not well formed");
        }

        XMLTree xmlTree = new XMLTree();
        XMLTree.Node rootNode = new XMLTree.Node(rootData);
        xmlTree.root = rootNode;

        // process child XML of the root node
        processXMLString(rootNode, xml.substring(rootEnd+1, closingRootIndex));

        return xmlTree;
    }



}
