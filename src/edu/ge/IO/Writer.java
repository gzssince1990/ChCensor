package edu.ge.IO;

import edu.ge.Model.Word;
import edu.ge.repository.WordRepository;
import edu.ge.search.Search;
import edu.ge.translator.Translator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhisong on 3/22/2016.
 */
@Service
public class Writer implements Runnable {

    @Autowired
    private WordRepository wordRepository;

    private final Pattern wordChPattern = Pattern.compile("(.+?)\\(");
    private final Pattern wordEnPattern = Pattern.compile("\\((.+?)\\)");

    private LineBag bag;
    private String fName;
    private Thread thread;

    public Writer(){}

    public Writer(LineBag bag, String tName, String fName){
        this.bag = bag;
        this.fName = fName;
        thread = new Thread(this, tName);
        thread.start();
    }

    public void init(LineBag bag, String tName, String fName){
        this.bag = bag;
        this.fName = fName;
        thread = new Thread(this, tName);
        thread.start();
    }

    public Thread getThread() {
        return thread;
    }

    public void run() {
        Search baidu = new Search(Search.Engine.BAIDU);
        Search sogou = new Search(Search.Engine.SOGOU);
        Search qihu = new Search(Search.Engine.QIHU);
        Translator translator = new Translator();

        //IOExcel ioExcel = new IOExcel("test1.xls");
        int i = 2;

        while (!bag.isEnd()){
            String line = bag.remove();
            /*
            Matcher wordChMatcher = wordChPattern.matcher(line);
            String wordCh = wordChMatcher.find()? wordChMatcher.group(1):null;
            Matcher wordEnMatcher = wordEnPattern.matcher(line);
            String wordEn = wordEnMatcher.find()? wordEnMatcher.group(1):null;
            */
            String wordCh = line.trim();
            String wordEn = translator.translate(wordCh);
            boolean chBaidu = baidu.isBlocked(wordCh);
            boolean chSogou = sogou.isBlocked(wordCh);
            boolean chQihu = qihu.isBlocked(wordCh);
            boolean enBaidu = baidu.isBlocked(wordEn);
            boolean enSogou = sogou.isBlocked(wordEn);
            boolean enQihu = qihu.isBlocked(wordEn);


            /*
            //System.out.println(wordCh +"    " + wordEn);
            ioExcel.writeRow(i++, new Object[]{wordCh, chBaidu, chSogou, chQihu,
                    wordEn, enBaidu, enSogou, enQihu, Calendar.getInstance().getTime()});
            */

            Word word = new Word();
            word.setWord(wordCh);
            word.setTranslation(wordEn);
            word.setBaidu_ch(chBaidu);
            word.setSogou_ch(chSogou);
            word.setQihu_ch(chQihu);
            word.setBaidu_en(enBaidu);
            word.setSogou_en(enSogou);
            word.setQihu_en(enQihu);
            wordRepository.addWord(word);
        }
    }
}
