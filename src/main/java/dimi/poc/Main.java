package dimi.poc;

import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.endpoint.GetEndpointImpl;
import dimi.poc.elasticsearch.model.Product;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Created by dimitrisaeys on 29/07/15.
 */
public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        Product product = new Product();
        product.setId("boormachine1");
        product.setDescription("Boormachine omschrijving");
        product.setTitle("Bosh boor");

        //Comment if already in elasticsearch.
//        AddEndpoint addEndpoint = new AddEndpoint();
//        addEndpoint.add(product, "products", "Boormachines", product.getId());

        GetEndpointImpl<Product> getEndpoint = new GetEndpointImpl<Product>(Product.class);
        Product product1 = getEndpoint.get("products", "Boormachines", product.getId());

        System.out.println("ID= "+ product1.getId());
    }
}
