package edu.ge.Model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by zhisong on 4/29/16.
 */
@Document(collection = "word")
public class Word {
    private String word;
    private int search_total;
    private int block_baidu;
    
}
