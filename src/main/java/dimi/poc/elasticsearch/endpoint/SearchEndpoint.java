package dimi.poc.elasticsearch.endpoint;

import java.util.List;

/**
 * Created by dimitrisaeys on 02/08/15.
 */
public interface SearchEndpoint<T> {
    List<T> search(final String index, final String query, final Class<T> receiveObjectClass);
}
