package edu.ge.controller;

import edu.ge.Model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/search")
public class SearchController {

    @RequestMapping(value = "/word", method = RequestMethod.GET)
    public String searchWordView(){
        return "search_word";
    }

    @RequestMapping(value = "/word", method = RequestMethod.POST)
    public void searchWord(HttpServletRequest req, HttpServletResponse res) throws IOException {
        PrintWriter out = res.getWriter();
        out.write("\"word\":\"" + req.getParameter("word") + "\"");
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

        Person person = new Person();
        person.setName(req.getParameter("username"));
        person.setAge(Integer.parseInt(req.getParameter("age")));

        res.setContentType("application/json");
        PrintWriter out = res.getWriter();
        out.write("{\"name\": \"ge\"}");
    }

}
