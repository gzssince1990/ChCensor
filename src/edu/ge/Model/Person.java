package edu.ge.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhisong on 4/27/16.
 */
@Document(collection = "person")
public class Person {
    //@Id
    //private String id;
    private String name;
    private int age;

    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

    public Person(){}

    /*
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
    */

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
