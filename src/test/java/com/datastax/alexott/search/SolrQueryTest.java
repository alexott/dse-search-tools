package com.datastax.alexott.search;

import com.fasterxml.jackson.core.JsonProcessingException;
import junit.framework.TestCase;

public class SolrQueryTest extends TestCase {

    public void testDefault () throws JsonProcessingException {
        SolrQuery solrQuery = SolrQuery.builder().build();
        assertEquals("{\"q\":\"*:*\"}", solrQuery.get());
    }

    public void testFq () throws JsonProcessingException {
        SolrQuery solrQuery = SolrQuery.builder().withFilterQuery("q:1").build();
        assertEquals("{\"q\":\"*:*\",\"fq\":\"q:1\"}", solrQuery.get());
    }

}