package edu.ge.Controller;

import edu.ge.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhisong on 4/27/16.
 */
@Controller
public class helloWorldController {

    @RequestMapping("/HelloWorld")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/person")
    public String toPerson(String name, double age){
        System.out.println(name+ " " + age);
        return "hello1";
    }

    @RequestMapping("/person1")
    public String toPerson(Person p){
        System.out.println(p.getName()+ " " + p.getAge());
        return "hello2";
    }
}
