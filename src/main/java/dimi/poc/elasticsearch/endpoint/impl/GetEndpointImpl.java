package dimi.poc.elasticsearch.endpoint.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import dimi.poc.elasticsearch.endpoint.GetEndpoint;
import dimi.poc.elasticsearch.factory.ClientFactory;
import org.elasticsearch.action.get.GetResponse;

import java.io.IOException;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
public class GetEndpointImpl<RECIEVE> implements GetEndpoint<RECIEVE> {
    private ObjectMapper mapper = new ObjectMapper();
    private final Class<RECIEVE> recieveObjectClass;

    public GetEndpointImpl(Class<RECIEVE> recieveObjectClass) {
        this.recieveObjectClass = recieveObjectClass;
    }

    @Override
    public RECIEVE get(String index, String type, String id) {

        GetResponse response = ClientFactory.getClient().prepareGet(index, type, id)
                .execute()
                .actionGet();

        RECIEVE product = null;
        try {
            product = mapper.readValue(response.getSourceAsString(), recieveObjectClass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return product;
    }

}
