package dimi.elasticsearch.poc;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

/**
 * Created by dimitrisaeys on 29/07/15.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world");

        Node node = nodeBuilder().clusterName("dimi_elasticsearch").node();
        Client client = node.client();


        node.close();
    }
}
