package edu.ge.controller;

import edu.ge.repository.PersonRepository;
import edu.ge.Model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by zhisong on 4/27/16.
 */
@Controller
public class helloWorldController {

    @Autowired
    private PersonRepository repository;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/person")
    public String toPerson(String name, double age){

        return "person";
    }

    @RequestMapping(value = "/person1", method = RequestMethod.POST)
    public @ResponseBody Person toPerson(Person p){
        System.out.println(p.getName()+ " " + p.getAge());
        repository.addPerson(p);
        return p;
    }

    @RequestMapping(value = "/person2")
    public @ResponseBody
    List<Person> getPersons(){
        return repository.getPersons();
    }

}
