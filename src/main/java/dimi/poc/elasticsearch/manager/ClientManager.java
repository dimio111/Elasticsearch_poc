package dimi.poc.elasticsearch.manager;

import com.google.inject.Singleton;
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

    private static final String CLUSTER_NAME = "cluster.name";

    private Client client = null;

    public ClientManager(String cluster, String host, int port) {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put(CLUSTER_NAME, cluster).build();
        client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress(host, port));
    }

    public Client getClient() {
        return client;
    }

    public void closeClient(){
        client.close();
    }
}
