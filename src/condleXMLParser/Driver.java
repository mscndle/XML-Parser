package condleXMLParser;

public class Driver {

    public static void main(String[] args) {

        XMLTree tree = new XMLTree("/Users/mscndle/Developer/IdeaProjects/XMLParser/sample.xml");

//        System.out.println(tree.root.data);

        for (XMLTree.Node node: tree.root.childNodes) {
            System.out.println("NODE: " + node.data);

            if (node.childNodes != null) {
                for (XMLTree.Node child: node.childNodes) {
                    System.out.println("\t" + "NODE: " + child.data);
                }
            }
        }

        System.out.println(tree.root.childNodes);


    }

}
