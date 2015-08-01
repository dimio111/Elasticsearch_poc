package dimi.poc.elasticsearch.endpoint;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public interface AddEndpoint<T> {
    boolean add(T object ,String index, String type, String id);
}
