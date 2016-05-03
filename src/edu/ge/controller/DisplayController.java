package edu.ge.controller;

import edu.ge.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by zhisong on 4/30/16.
 */
@Controller
@RequestMapping("/display")
public class DisplayController {

    @Autowired
    private WordRepository wordRepository;

    @RequestMapping(value = "/data", method = RequestMethod.GET)
    public String listData(ModelMap model){
        model.addAttribute("wordList", wordRepository.getWords());
        return "display_data";
    }

}
