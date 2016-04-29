package edu.ge.repository;

import edu.ge.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhisong on 4/29/16.
 */
@Repository
public class PersonRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    public void addPerson(Person person){
        mongoTemplate.insert(person);
    }

    public List<Person> getPersons(){
        return mongoTemplate.findAll(Person.class);
    }

}
