package dimi.poc.elasticsearch.guice;

import com.google.inject.AbstractModule;
import dimi.poc.elasticsearch.endpoint.AddEndpoint;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.endpoint.impl.AddEndpointImpl;
import dimi.poc.elasticsearch.endpoint.impl.GetEndpointImpl;
import dimi.poc.elasticsearch.guice.provider.ClientManagerProvider;
import dimi.poc.elasticsearch.manager.ClientManager;
import dimi.poc.elasticsearch.service.product.ProductService;
import dimi.poc.elasticsearch.service.product.impl.ProductServiceImpl;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ElasticApiModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ClientManager.class).toProvider(ClientManagerProvider.class);
        bind(GetEndpoint.class).to(GetEndpointImpl.class);
        bind(AddEndpoint.class).to(AddEndpointImpl.class);
        bind(ProductService.class).to(ProductServiceImpl.class);
    }
}
