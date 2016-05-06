package edu.ge.controller;

import edu.ge.Model.Word;
import edu.ge.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @RequestMapping(value = "/visual", method = RequestMethod.GET)
    public String displayData(){
        return "display_visual";
    }

    /**
     * TODO visualize json data on client side
     * TODO 1. Word map with different visual ranges that represent search engines;
     * TODO 2. Try to group words according to banned search engine;
     * TODO 3. Try to produce the chart like in excel;
     * TODO 4. Group percentage; 30%,50%,80% blocked.
     * TODO 4. Whatever I can do;
     * @return
     */
    @RequestMapping(value = "/visual", method = RequestMethod.POST)
    public @ResponseBody
    List<Word> visualizeData(){
        return wordRepository.getWords();
    }
}
