package dimi.poc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import dimi.poc.elasticsearch.config.ElasticPocConfig;
import dimi.poc.elasticsearch.guice.ElasticApiModule;
import dimi.poc.elasticsearch.resource.ProductResource;
import dimi.poc.elasticsearch.service.product.ProductService;
import io.dropwizard.Application;
import org.apache.log4j.Logger;
import io.dropwizard.setup.Environment;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ElasticPoc extends Application<ElasticPocConfig> {

    private final static Logger LOG = Logger.getLogger(ElasticPoc.class);

    public static void main(String[] args) throws Exception {
        new ElasticPoc().run(args);
    }

    @Override
    public void run(ElasticPocConfig configuration, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new ElasticApiModule());
        final ProductResource productResource = new ProductResource(injector.getInstance(ProductService.class));
        environment.jersey().register(productResource);
    }
}
