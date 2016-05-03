package edu.ge.controller;

import edu.ge.IO.IO;
import edu.ge.repository.PersonRepository;
import edu.ge.Model.Person;
import edu.ge.search.Search;
import edu.ge.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by zhisong on 4/27/16.
 */
@Controller
@EnableAsync
public class helloWorldController {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private Search search;

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

    @RequestMapping("/person")
    public String toPerson(){
        String uploadDirStr = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
                + File.separator + "/upload";
        String orgText = IO.readFile(uploadDirStr,"test.txt");

        String[] orgWords = orgText.split("\n");

        Translator translator =new Translator();


        for (int i = 0; i < orgWords.length; i++) {
            //System.out.println(orgWords[i] + '\t' + Translator.translate(orgWords[i]));
            IO.writeFile(uploadDirStr, "out.txt", orgWords[i] + '\t' + translator.translate(orgWords[i]));
        }
        return "person";
    }

    @RequestMapping(value = "/person1", method = RequestMethod.POST)
    public @ResponseBody Person toPerson(Person p) throws InterruptedException {
        System.out.println("Entering controller");
        System.out.println(p.getName()+ " " + p.getAge());
        repository.addPerson(p);
        search.setEngine(Search.Engine.BAIDU);
        search.isBlocked("falungong");
        search.testAsync();
        System.out.println("Leaving controller");
        return p;
    }

    @RequestMapping(value = "/person2")
    public @ResponseBody
    List<Person> getPersons(){
        return repository.getPersons();
    }
}
