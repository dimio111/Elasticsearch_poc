package dimi.poc.elasticsearch.manager;

import com.google.inject.Singleton;
import dimi.poc.elasticsearch.config.parts.ElasticSearchConfig;
import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.net.ConnectException;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
@Singleton
public class ClientManager {
    private final static Logger LOG = Logger.getLogger(ClientManager.class);

    private Client client = null;

    public Client getClient() {
        return client;
    }

    public void closeClient(){
        LOG.info("Closing Elasticsearch client connection");
        this.client.close();
    }

    public void configureAndStartClientManager(ElasticSearchConfig elasticSearchConfig) throws ConnectException {
        LOG.info("Configuring clientManager [cluster|"+elasticSearchConfig.getCluster()+"] [host|"+elasticSearchConfig.getHost()+"] [port|"+elasticSearchConfig.getPort()+"]");
        LOG.info("Opening client connection");
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", elasticSearchConfig.getCluster()).build();
        this.client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(elasticSearchConfig.getHost(), elasticSearchConfig.getPort()));
        if(client != null){
            LOG.info("Connection successful");
        }else{
            throw new ConnectException("Could not connect to elasticsearch");
        }
        createShutdownHook();
    }

    private void createShutdownHook(){
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {closeClient(); }
        });
    }
}
