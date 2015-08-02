package dimi.poc.elasticsearch.endpoint.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import dimi.poc.elasticsearch.endpoint.SearchEndpoint;
import dimi.poc.elasticsearch.manager.ClientManager;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dimitrisaeys on 02/08/15.
 */
public class SearchEndPointImpl<QUERY> implements SearchEndpoint<QUERY> {

    @Inject
    private ClientManager clientManager;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<QUERY> search(final String index, final String query, final Class<QUERY> receiveObjectClass) {
        SearchResponse response = clientManager.getClient().prepareSearch(index)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(QueryBuilders.queryStringQuery(query))
                .execute()
                .actionGet();

        return createQueryList(response, receiveObjectClass);
    }

    private List<QUERY> createQueryList(final SearchResponse searchResponse, final Class<QUERY> receiveObjectClass){
        List<QUERY> queryResults = new ArrayList<>();

        SearchHit[] results = searchResponse.getHits().getHits();
        try {
            for (SearchHit hit : results) {
                queryResults.add(mapper.readValue(hit.getSourceAsString(), receiveObjectClass));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return queryResults;
    }
}
