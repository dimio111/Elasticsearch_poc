package dimi.poc.elasticsearch.manager;

import com.google.inject.Singleton;
import io.dropwizard.cli.Cli;
import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
@Singleton
public class ClientManager {
    private final static Logger LOG = Logger.getLogger(ClientManager.class);

    private Client client = null;
    private String cluster;
    private String host;
    private int port;

    //TODO needs to get injected!
    public ClientManager(String cluster, String host, int port) {
        this.cluster = "dimi_elasticsearch";
        this.host = "localhost";
        this.port = 9300;
    }

    public ClientManager() {
        this.cluster = "dimi_elasticsearch";
        this.host = "localhost";
        this.port = 9300;
    }

    public Client getClient() {
        return client;
    }

    public Client openClient() {
        LOG.info("Opening client connection");
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", cluster).build();
        this.client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(host, port));
        return this.client;
    }

    public void closeClient(){
        LOG.info("Closing client connection");
        this.client.close();
    }
}
