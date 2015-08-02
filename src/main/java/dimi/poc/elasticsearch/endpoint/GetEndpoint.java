package dimi.poc.elasticsearch.endpoint;


/**
 * Created by dimitrisaeys on 31/07/15.
 */
public interface GetEndpoint<T> {
    T get(String index, String type, String id, Class<T> receiveObjectClass);
}
