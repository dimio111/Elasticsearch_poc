package dimi.poc.elasticsearch.service.product.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.model.Product;
import dimi.poc.elasticsearch.service.product.ProductService;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
@Singleton
public class ProductServiceImpl implements ProductService{

    private GetEndpoint<Product> getEndpoint;
    private AddEndpoint<Product> addEndpoint;

    @Inject
    public ProductServiceImpl(GetEndpoint<Product> getEndpoint, AddEndpoint<Product> addEndpoint) {
        this.getEndpoint = getEndpoint;
        this.addEndpoint = addEndpoint;
    }

    @Override
    public Product getProduct(String index, String type, String id) {
        return getEndpoint.get(index, type, id, Product.class);
    }

    @Override
    public boolean addProduct(Product product, String index, String type) {
        return addEndpoint.add(product, index, type, product.getId());
    }
}
