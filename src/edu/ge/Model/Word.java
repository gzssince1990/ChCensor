package edu.ge.Model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import java.util.Date;

/**
 * Created by zhisong on 4/29/16.
 */
@Document(collection = "word")
public class Word {
    private String word;
    private boolean baidu_ch;
    private boolean sogou_ch;
    private boolean qihu_ch;
    private String translation;
    private boolean baidu_en;
    private boolean sogou_en;
    private boolean qihu_en;
    @DateTimeFormat(iso = ISO.DATE_TIME)
    private Date createDate;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isBaidu_ch() {
        return baidu_ch;
    }

    public void setBaidu_ch(boolean baidu_ch) {
        this.baidu_ch = baidu_ch;
    }

    public boolean isSogou_ch() {
        return sogou_ch;
    }

    public void setSogou_ch(boolean sogou_ch) {
        this.sogou_ch = sogou_ch;
    }

    public boolean isQihu_ch() {
        return qihu_ch;
    }

    public void setQihu_ch(boolean qihu_ch) {
        this.qihu_ch = qihu_ch;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean isBaidu_en() {
        return baidu_en;
    }

    public void setBaidu_en(boolean baidu_en) {
        this.baidu_en = baidu_en;
    }

    public boolean isSogou_en() {
        return sogou_en;
    }

    public void setSogou_en(boolean sogou_en) {
        this.sogou_en = sogou_en;
    }

    public boolean isQihu_en() {
        return qihu_en;
    }

    public void setQihu_en(boolean qihu_en) {
        this.qihu_en = qihu_en;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
