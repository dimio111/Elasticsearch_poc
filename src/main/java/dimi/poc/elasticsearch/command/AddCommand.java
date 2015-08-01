package dimi.poc.elasticsearch.command;

import dimi.poc.elasticsearch.endpoint.impl.AddEndpointImpl;
import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.model.Product;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class AddCommand {

    public static boolean addProduct(final Product product, final String index, final String type, final String id){
        AddEndpoint<Product> addEndpoint = new AddEndpointImpl<>();
        return addEndpoint.add(product, index, type, id);
    }
}
