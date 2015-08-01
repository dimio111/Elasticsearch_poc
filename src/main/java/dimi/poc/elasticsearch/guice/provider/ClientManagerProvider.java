package dimi.poc.elasticsearch.guice.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;
import dimi.poc.elasticsearch.config.parts.ElasticSearchConfig;
import dimi.poc.elasticsearch.manager.ClientManager;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ClientManagerProvider implements Provider<ClientManager> {

    private final ElasticSearchConfig elasticSearchConfig;

    //TODO this injection is not working!!!
    @Inject
    public ClientManagerProvider(ElasticSearchConfig elasticSearchConfig) {
        this.elasticSearchConfig = elasticSearchConfig;
    }

    @Override
    public ClientManager get() {
        return new ClientManager(elasticSearchConfig.getCluster(),
                elasticSearchConfig.getHost(), elasticSearchConfig.getPort());
    }
}
