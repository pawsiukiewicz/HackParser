package pl.destyl.hackyah.hackparser.db.dto;

/**
 * Created by destyl on 2017-10-28.
 */
public class Dictionary {
    private int dic_id;

    private String dic_word;

    private int dic_count;

    private int dic_sum_in_all;

    private int dic_in_article;

    private int dic_prioryty;

    public int getDic_id() {
        return dic_id;
    }

    public void setDic_id(int dic_id) {
        this.dic_id = dic_id;
    }

    public String getDic_word() {
        return dic_word;
    }

    public void setDic_word(String dic_word) {
        this.dic_word = dic_word;
    }

    public int getDic_count() {
        return dic_count;
    }

    public void setDic_count(int dic_count) {
        this.dic_count = dic_count;
    }

    public int getDic_sum_in_all() {
        return dic_sum_in_all;
    }

    public void setDic_sum_in_all(int dic_sum_in_all) {
        this.dic_sum_in_all = dic_sum_in_all;
    }

    public int getDic_in_article() {
        return dic_in_article;
    }

    public void setDic_in_article(int dic_in_article) {
        this.dic_in_article = dic_in_article;
    }

    public int getDic_prioryty() {
        return dic_prioryty;
    }

    public void setDic_prioryty(int dic_prioryty) {
        this.dic_prioryty = dic_prioryty;
    }
    public String toString() {
        return "id="+dic_id+" word="+dic_word+" count"+dic_count;
    }
}
