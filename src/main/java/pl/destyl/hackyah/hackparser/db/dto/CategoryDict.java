package pl.destyl.hackyah.hackparser.db.dto;

/**
 * Created by destyl_2 on 2017-10-28.
 */
public class CategoryDict {
    private int cdt_prd_id;

    private int cdt_dic_id;

    private int cdt_count;

    private int cdt_sum_in_all;

    private int cdt_in_article;

    private int cdt_priority;

    public int getCdt_prd_id() {
        return cdt_prd_id;
    }

    public void setCdt_prd_id(int cdt_prd_id) {
        this.cdt_prd_id = cdt_prd_id;
    }

    public int getCdt_dic_id() {
        return cdt_dic_id;
    }

    public void setCdt_dic_id(int cdt_dic_id) {
        this.cdt_dic_id = cdt_dic_id;
    }

    public int getCdt_count() {
        return cdt_count;
    }

    public void setCdt_count(int cdt_count) {
        this.cdt_count = cdt_count;
    }

    public int getCdt_sum_in_all() {
        return cdt_sum_in_all;
    }

    public void setCdt_sum_in_all(int cdt_sum_in_all) {
        this.cdt_sum_in_all = cdt_sum_in_all;
    }

    public int getCdt_in_article() {
        return cdt_in_article;
    }

    public void setCdt_in_article(int cdt_in_article) {
        this.cdt_in_article = cdt_in_article;
    }

    public int getCdt_priority() {
        return cdt_priority;
    }

    public void setCdt_priority(int cdt_priority) {
        this.cdt_priority = cdt_priority;
    }
}
