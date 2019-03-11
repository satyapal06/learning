package com.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.elasticsearch.domain.Person;
import com.knoldus.EsClient;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import java.io.IOException;
import java.util.*;

public class JavaClientService {
    private final Client client = EsClient.client();
    private final QueryServices queryServices = new QueryServices();

    private static final String INDEX = "people";
    private static final String TYPE = "person";

    public IndexResponse add(String firstName, String lastName, String address, Integer age, String id) {
        try {
            XContentBuilder builder = XContentFactory.jsonBuilder()
                    .startObject()
                    .field("personId", id)
                    .field("firstName", firstName)
                    .field("lastName", lastName)
                    .field("address", address)
                    .field("dateOfBirth", new Date())
                    .field("age", age)
                    .endObject();

            IndexResponse response = client.prepareIndex(INDEX, TYPE, id).setSource(builder).get();

            String responseId = response.getId();
            String index = response.getIndex();
            String type = response.getType();
            long version = response.getVersion();

            System.out.println("Response Id: " + responseId);
            System.out.println("Index: " + index);
            System.out.println("Type: " + type);
            System.out.println("Version: " + version);

            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Person insertPerson(Person person){
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("personId", person.getPersonId());
        dataMap.put("firstName", person.getFirstName());
        dataMap.put("lastName", person.getLastName());
        dataMap.put("address", person.getAddress());
        dataMap.put("dateOfBirth", person.getDateOfBirth());
        dataMap.put("age", person.getAge());
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, person.getPersonId())
                .source(dataMap);
        try {
            client.index(indexRequest, new ActionListener<IndexResponse>() {

                @Override
                public void onResponse(IndexResponse indexResponse) {
                    System.out.println(indexResponse.isCreated());
                }

                @Override
                public void onFailure(Throwable e) {
                    e.printStackTrace();
                }
            });
        } catch(ElasticsearchException e) {
            e.getDetailedMessage();
        }

        return person;
    }

    public List<DeleteResponse> deleteAll() {
        List<Person> list = queryServices.searchAll();
        List<DeleteResponse> responses = new ArrayList<>();
        list.forEach(personObj -> {
            String personId = personObj.getPersonId();
            DeleteResponse response = client.prepareDelete(INDEX, TYPE, personId.trim()).get();
            System.out.println(response);
            responses.add(response);
        });

        return responses;
    }

    public void deleteAllByIndexName() {
        DeleteIndexRequest request = new DeleteIndexRequest(INDEX);

        request.timeout(TimeValue.timeValueMinutes(2));
        request.timeout("2m");

        DeleteIndexResponse deleteResponse = client.admin().indices().delete(request).actionGet();
        System.out.println(deleteResponse.toString());
        client.admin().indices().prepareCreate(INDEX).get();
    }

    public QueryServices getQueryServices() {
        return queryServices;
    }

    public static void main(String args[]) {
        JavaClientService javaClientService = new JavaClientService();
        javaClientService.deleteAllByIndexName();
        javaClientService.add("Suman", "Singh", "New Delhi", 29, "suman16119089@gmail.com");
        Person person = new Person("satyapal06@gmail.com", "Satya Pal", "Singh", "New Delhi", new Date(), 30);
        javaClientService.insertPerson(person);

        List<Person> list = javaClientService.getQueryServices().searchAll();
        list.forEach(personObj -> {
            String personString = personObj.toString();
            System.out.println(personString);
        });
    }
}
