package edu.ge.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhisong on 4/28/16.
 */
@Controller
@RequestMapping("/")
public class HomeController {

    @RequestMapping("/")
    public String index(){
        return "redirect:/search/word";
    }
}
