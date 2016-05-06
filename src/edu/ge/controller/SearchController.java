package edu.ge.controller;

import edu.ge.IO.IO;
import edu.ge.Model.Person;
import edu.ge.Model.Word;
import edu.ge.repository.WordRepository;
import edu.ge.search.Search;
import edu.ge.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by zhisong on 4/28/16.
 */
@Controller
@EnableAsync
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private Search search;

    @Autowired
    private Translator translator;

    @Autowired
    private IO io;

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public String searchWordView(){
        return "search_word";
    }

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public @ResponseBody
    Word searchWord(Word word) throws IOException, InterruptedException {
        search.setEngine(Search.Engine.BAIDU);
        word.setTranslation(translator.translate(word.getWord()));
        word.setBaidu_ch(search.isBlocked(word.getWord()));
        word.setBaidu_en(search.isBlocked(word.getTranslation()));
        search.setEngine(Search.Engine.SOGOU);
        word.setSogou_ch(search.isBlocked(word.getWord()));
        word.setSogou_en(search.isBlocked(word.getTranslation()));
        search.setEngine(Search.Engine.QIHU);
        word.setQihu_ch(search.isBlocked(word.getWord()));
        word.setQihu_en(search.isBlocked(word.getTranslation()));
        wordRepository.addWord(word);
        return word;
    }

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String searchFileView(){
        return "search_file";
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public void searchFile(@RequestParam("file")CommonsMultipartFile file, HttpServletRequest req, HttpServletResponse res) throws IOException {
        String fileName = file.getOriginalFilename();

        String uploadDirStr = FileSystemView.getFileSystemView().getDefaultDirectory().getPath()
                + File.separator + "/upload";
        File uploadDir = new File(uploadDirStr);
        File uploadPath = new File(uploadDir, fileName);
        if (!uploadDir.exists()){
            uploadDir.mkdirs();
        } else if (!uploadDir.isDirectory()){
            uploadDir.delete();
            uploadDir.mkdirs();
        }
        file.transferTo(uploadPath);

        //Proccess data
        io.addWordsFromFile(uploadPath.getAbsolutePath());


        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.write("{\"statusCode\": \"200\",\"uploadPath\":\""+ uploadPath +"\"}");
        System.out.println("Leaving search file controller");
    }

}
