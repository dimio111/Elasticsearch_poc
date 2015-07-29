package dimi.elasticsearch.poc;

import com.fasterxml.jackson.databind.ObjectMapper;
import dimi.elasticsearch.poc.model.Product;
import org.apache.commons.lang3.RandomUtils;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

/**
 * Created by dimitrisaeys on 29/07/15.
 */
public class Main {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        System.out.println("Hello world");

        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "dimi_elasticsearch").build();
        Client client = new TransportClient(settings)
                .addTransportAddress(new InetSocketTransportAddress("localhost", 9300));

        String[] uuids = new String[10];
        Integer[] ids = new Integer[10];

        ObjectMapper mapper = new ObjectMapper();

        for (int i = 0 ; i < uuids.length ; i++){
            Product product = new Product();
            uuids[i] = UUID.randomUUID().toString();
            product.setId(uuids[i]);
            product.setTitle("Title" + i);
            product.setDescription("Description" + i);

            ids[i] = RandomUtils.nextInt(0,100);

            byte[] json = mapper.writeValueAsBytes(product);

            System.out.println("DIMI- creating index " + uuids[i]);

            IndexRequest indexRequest = new IndexRequest("products", uuids[i], ids[i].toString())
                    .source(json);

            UpdateRequest updateRequest = new UpdateRequest("products", uuids[i], ids[i].toString())
                    .doc(json)
                    .upsert(indexRequest);

            System.out.println("Upserting " + uuids[i]);

            client.update(updateRequest).get();


        }

        for (int i = 0 ; i < uuids.length ; i++){
            GetResponse response = client.prepareGet("products", uuids[i], ids[i].toString())
                    //.setOperationThreaded(false)
                    .execute()
                    .actionGet();

            Product product = mapper.readValue(response.getSourceAsString(), Product.class);
            System.out.println(product.getId() + "/"+ids[i].toString());
            System.out.println(product.getTitle());
            System.out.println(product.getDescription());
        }

        client.close();

    }
}
