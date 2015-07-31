package dimi.poc.elasticsearch.factory;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
public class ClientFactory {

    static private Client client = null;

    static{
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "dimi_elasticsearch").build();
        client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
    }

    public static Client getClient() {
        return client;
    }

    public static void closeClient(){
        client.close();
    }
}
