package dimi.poc.elasticsearch.service.product.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.endpoint.SearchEndpoint;
import dimi.poc.elasticsearch.model.Product;
import dimi.poc.elasticsearch.service.product.ProductService;

import java.util.List;

import static dimi.poc.elasticsearch.Constants.PRODUCT_INDEX;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
@Singleton
public class ProductServiceImpl implements ProductService{

    @Inject
    private GetEndpoint<Product> getEndpoint;
    @Inject
    private AddEndpoint<Product> addEndpoint;
    @Inject
    private SearchEndpoint<Product> searchEndpoint;

    @Override
    public Product getProduct(String index, String type, String id) {
        return getEndpoint.get(index, type, id, Product.class);
    }

    @Override
    public boolean addProduct(Product product, String index, String type) {
        return addEndpoint.add(product, index, type, product.getId());
    }

    @Override
    public List<Product> search(String query) {
        return searchEndpoint.search(PRODUCT_INDEX, query, Product.class);
    }
}
