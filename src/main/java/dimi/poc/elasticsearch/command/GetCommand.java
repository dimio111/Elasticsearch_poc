package dimi.poc.elasticsearch.command;

import dimi.poc.elasticsearch.endpoint.impl.GetEndpointImpl;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.model.Product;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class GetCommand {

    public static Product getProduct(final String index, final String type, final String id) {
        GetEndpoint<Product> getEndpoint = new GetEndpointImpl<>(Product.class);
        return getEndpoint.get(index, type, id);
    }

}
