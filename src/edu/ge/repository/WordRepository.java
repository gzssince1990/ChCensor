package edu.ge.repository;

import edu.ge.Model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by zhisong on 4/29/16.
 */
@Repository
public class WordRepository {

    @Autowired
    private MongoTemplate mongoTemplate;


    public void addWord(Word word){
        word.setCreateDate(new Date());
        mongoTemplate.insert(word);
    }

    public Word getWordByWordStr(String wordStr){
        return mongoTemplate.findOne(Query.query(Criteria.where("word").is(wordStr)), Word.class);
    }

    public List<Word> getWords(){
        return mongoTemplate.findAll(Word.class);
    }

    public void deleteWord(String wordStr){
        mongoTemplate.findAllAndRemove(Query.query(Criteria.where("word").is(wordStr)),
                Word.class);
    }

    public void updateWord(String wordStr, Word word){
        Query query = new Query();
        query.addCriteria(Criteria.where("word").is(wordStr));

        Update update = new Update();
        update.set("baidu_ch", word.isBaidu_ch());
        update.set("sogou_ch", word.isSogou_ch());
        update.set("qihu_ch", word.isQihu_ch());
        update.set("baidu_en", word.isBaidu_en());
        update.set("sogou_en", word.isSogou_en());
        update.set("qihu_en", word.isQihu_en());
        update.set("createDate", new Date());

        mongoTemplate.updateFirst(query, update, Word.class);
    }
}
