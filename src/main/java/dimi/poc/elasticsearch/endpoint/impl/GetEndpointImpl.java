package dimi.poc.elasticsearch.endpoint.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.manager.ClientManager;
import org.elasticsearch.action.get.GetResponse;

import java.io.IOException;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
@Singleton
public class GetEndpointImpl<RECIEVE> implements GetEndpoint<RECIEVE> {

    @Inject
    private ClientManager clientManager;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public RECIEVE get(String index, String type, String id, Class<RECIEVE> receiveObjectClass) {

        GetResponse response = clientManager.getClient().prepareGet(index, type, id)
                .execute()
                .actionGet();

        RECIEVE product = null;
        try {
            product = mapper.readValue(response.getSourceAsString(), receiveObjectClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }

}
