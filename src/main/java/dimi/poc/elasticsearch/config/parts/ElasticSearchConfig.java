package dimi.poc.elasticsearch.config.parts;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by dimitrisaeys on 01/08/15.
 */
public class ElasticSearchConfig {

    @NotEmpty
    private String cluster;
    @NotEmpty
    private String host;
    @NotEmpty
    private int port;

    @JsonProperty
    public String getCluster() {
        return cluster;
    }

    @JsonProperty
    public String getHost() {
        return host;
    }

    @JsonProperty
    public int getPort() {
        return port;
    }
}
