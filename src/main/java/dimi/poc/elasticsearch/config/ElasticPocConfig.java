package dimi.poc.elasticsearch.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import dimi.poc.elasticsearch.config.parts.ElasticSearchConfig;
import io.dropwizard.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ElasticPocConfig extends Configuration {

    @NotNull
    private ElasticSearchConfig elasticSearchConfig;

    @JsonProperty("elasticsearch")
    public ElasticSearchConfig getElasticSearchConfig() {
        return elasticSearchConfig;
    }
}
