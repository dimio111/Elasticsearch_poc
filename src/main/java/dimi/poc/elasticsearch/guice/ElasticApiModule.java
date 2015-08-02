package dimi.poc.elasticsearch.guice;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.endpoint.SearchEndpoint;
import dimi.poc.elasticsearch.endpoint.impl.AddEndpointImpl;
import dimi.poc.elasticsearch.endpoint.impl.GetEndpointImpl;
import dimi.poc.elasticsearch.endpoint.impl.SearchEndPointImpl;
import dimi.poc.elasticsearch.manager.ClientManager;
import dimi.poc.elasticsearch.model.Product;
import dimi.poc.elasticsearch.service.product.ProductService;
import dimi.poc.elasticsearch.service.product.impl.ProductServiceImpl;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ElasticApiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ClientManager.class);

        //Endpoints
        bind(new TypeLiteral<GetEndpoint<Product>>() {}).to(new TypeLiteral<GetEndpointImpl<Product>>() {});
        bind(new TypeLiteral<AddEndpoint<Product>>() {}).to(new TypeLiteral<AddEndpointImpl<Product>>() {});
        bind(new TypeLiteral<SearchEndpoint<Product>>() {}).to(new TypeLiteral<SearchEndPointImpl<Product>>() {});

        //Services
        bind(ProductService.class).to(ProductServiceImpl.class);
    }
}
