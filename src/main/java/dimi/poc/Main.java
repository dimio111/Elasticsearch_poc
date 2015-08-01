package dimi.poc;

import dimi.poc.elasticsearch.command.AddCommand;
import dimi.poc.elasticsearch.command.GetCommand;
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
        boolean productAdded = AddCommand.addProduct(product, "products", "Boormachines", product.getId());

        Product product1 = GetCommand.getProduct("products", "Boormachines", product.getId());

        System.out.println("ID= " + product1.getId());
    }
}
