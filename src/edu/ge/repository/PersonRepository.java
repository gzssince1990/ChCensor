package edu.ge.repository;

import edu.ge.Model.Person;
import edu.ge.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhisong on 4/29/16.
 */
@Repository
public class PersonRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private Search search;

    @Async
    public void addPerson(Person person) throws InterruptedException {
        System.out.println("Entering addPerson method");
        Thread.sleep(5000);
        search.creatSearch(Search.Engine.BAIDU).isBlocked("falungong");
        Thread.sleep(5000);
        mongoTemplate.insert(person);
        System.out.println("Leaving addPerson method");
    }

    public List<Person> getPersons(){
        return mongoTemplate.findAll(Person.class);

    }

}
