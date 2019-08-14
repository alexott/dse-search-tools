package com.datastax.alexott.search;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/*
 * Class for easier building of the Solr queries for DSE.
 *
 * https://docs.datastax.com/en/dse/5.1/dse-dev/datastax_enterprise/search/siQuerySyntax.html#siQuerySyntax
 */
public class SolrQuery {
    final static String DEFAULT_QUERY = "*:*";
    final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    final String query;

    private SolrQuery() {
        query = DEFAULT_QUERY;
    }

    private SolrQuery(String q) {
        query = q;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {
        @JsonProperty
        String q = DEFAULT_QUERY;
        @JsonProperty
        String fq = null;
        @JsonProperty
        String sort = null;
        @JsonProperty
        String paging = null;
        @JsonProperty
        Integer start = null;
        @JsonProperty("distrib.singlePass")
        Boolean singlePass = null;
        @JsonProperty("shards.failover")
        Boolean shardsFailover = null;
        @JsonProperty("shards.tolerant")
        Boolean shardsTolerant = null;
        @JsonProperty
        Boolean commit = null;
        @JsonProperty("route.partition")
        List<String> routePartition = null;
        @JsonProperty("route.range")
        List<String> routeRange = null;
        @JsonProperty("query.name")
        String queryName = null;
        @JsonProperty
        Map<String, Object> facet = null;

        private Builder() {}

        public Builder withQuery(final String q) {
            this.q = q;
            return this;
        }

        public Builder withFilterQuery(final String fq) {
            this.fq = fq;
            return this;
        }

        public Builder withPaging(final String pg) {
            this.paging = pg;
            return this;
        }

        public Builder withSort(final String srt) {
            this.sort = srt;
            return this;
        }

        // TODO: add function that will take field name & direction, and append to sort string


        public Builder withQueryName(final String qn) {
            this.queryName = qn;
            return this;
        }

        // TODO: don't have enabled/disabled, but instead allow to change only non-default value
        public Builder withSinglePassEnabled() {
            this.singlePass = true;
            return this;
        }

        public Builder withSinglePassDisabled() {
            this.singlePass = false;
            return this;
        }

        public Builder withCommitEnabled() {
            this.commit = true;
            return this;
        }

        public Builder withFacet(Map<String, Object> fc) {
            facet = fc;
            return this;
        }

        // TODO: add withFacet that will take a Facet instance

        public SolrQuery build() throws JsonProcessingException {
            String query = OBJECT_MAPPER.writeValueAsString(this);
            // System.out.println("Generated query: '" + query + "'");
            return new SolrQuery(query);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String get() {
        return query;
    }

}
