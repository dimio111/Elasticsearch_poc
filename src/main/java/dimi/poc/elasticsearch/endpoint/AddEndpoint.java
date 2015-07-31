package dimi.poc.elasticsearch.endpoint;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dimi.poc.elasticsearch.factory.ClientFactory;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import java.util.concurrent.ExecutionException;

/**
 * Created by dimitrisaeys on 30/07/15.
 */
public class AddEndpoint {

    public boolean add(Object object, String index, String type, String id){
        byte[] json = transformToJson(object);
        boolean returner = false;

        if(json.length > 0 && StringUtils.isNotBlank(index) && StringUtils.isNotEmpty(type)){
            IndexRequest indexRequest = createIndexRequest(json, index, type, id);
            UpdateRequest updateRequest = createUpdateRequest(json, index, type, indexRequest);
            try {
                ClientFactory.getClient().update(updateRequest).get();
                returner = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return  returner;
    }


    private IndexRequest createIndexRequest(byte[] json, String index, String type, String id){
        if(StringUtils.isNotEmpty(id)){
            return new IndexRequest(index, type, id)
                    .source(json);
        }else{
            return new IndexRequest(index, type)
                    .source(json);
        }
    }

    private UpdateRequest createUpdateRequest(byte[] json, String index, String type, IndexRequest indexRequest){
            return new UpdateRequest(index, type, indexRequest.id())
                    .doc(json)
                    .upsert(indexRequest);
    }

    private byte[] transformToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        byte[] json = new byte[0];

        try {
            if(object != null){
                json = mapper.writeValueAsBytes(object);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
