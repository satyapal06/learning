package com.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.domain.Person;
import com.knoldus.EsClient;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryServices {
    private final Client client = EsClient.client();

    private static final String INDEX = "people";
    private static final String TYPE = "person";

    public List<Person> searchByMatchAllQuery() {
        QueryBuilder matchAllQuery = QueryBuilders.matchAllQuery();
        return getResult(matchAllQuery);
    }

    public List<Person> searchByWithinRange() {
        QueryBuilder matchDocumentsWithinRange = QueryBuilders
                .rangeQuery("age").from(30).to(100);

        return getResult(matchDocumentsWithinRange);
    }

    public List<Person> searchByMatchQuery() {
        QueryBuilder matchSpecificFieldQuery = QueryBuilders
                .matchQuery("firstName", "Suman");

        return getResult(matchSpecificFieldQuery);
    }

    public List<Person> searchAll() {
        client.admin().indices().prepareRefresh(INDEX).get();
        SearchResponse response = client.prepareSearch(INDEX).execute().actionGet();
        List<Person> results = getPeople(response);
        return results;
    }

    private List<Person> getPeople(SearchResponse response) {
        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        List<Person> results = new ArrayList<Person>();
        searchHits.forEach(hit -> {
            String jsonObject = hit.getSourceAsString();
            System.out.println(jsonObject);
            Person person = JSON.parseObject(jsonObject, Person.class);
            results.add(person);
        });
        return results;
    }

    private List<Person> getResult(QueryBuilder matchDocumentsWithinRange) {
        client.admin().indices().prepareRefresh(INDEX).get();
        SearchResponse response = client.prepareSearch(INDEX).setTypes(TYPE)
                .setQuery(matchDocumentsWithinRange).execute().actionGet();
        List<Person> results = getPeople(response);
        return results;
    }

    public static void main(String args[]) {
        QueryServices queryServices = new QueryServices();
        //queryServices.searchByMatchAllQuery();
        //queryServices.searchByWithinRange();
        queryServices.searchByMatchQuery();
    }
}
