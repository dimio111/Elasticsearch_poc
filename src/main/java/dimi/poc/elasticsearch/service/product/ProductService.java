package dimi.poc.elasticsearch.service.product;

import dimi.poc.elasticsearch.model.Product;

import java.util.List;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public interface ProductService {
    Product getProduct(String index, String type, String id);
    boolean addProduct(Product product, String index, String type);
    List<Product> search(String query);
}
