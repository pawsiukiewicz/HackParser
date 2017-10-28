package pl.destyl.hackyah.hackparser.db.dto;

/**
 * Created by destyl on 2017-10-28.
 */
public class ProductsDict {

    private int pdc_prd_id;

    private int pdc_dic_id;

    private int pdc_count;

    private int pdc_sum_in_all;

    private int pdc_in_article;

    private int pdc_priority;

    public int getPdc_prd_id() {
        return pdc_prd_id;
    }

    public void setPdc_prd_id(int pdc_prd_id) {
        this.pdc_prd_id = pdc_prd_id;
    }

    public int getPdc_dic_id() {
        return pdc_dic_id;
    }

    public void setPdc_dic_id(int pdc_dic_id) {
        this.pdc_dic_id = pdc_dic_id;
    }

    public int getPdc_count() {
        return pdc_count;
    }

    public void setPdc_count(int pdc_count) {
        this.pdc_count = pdc_count;
    }

    public int getPdc_sum_in_all() {
        return pdc_sum_in_all;
    }

    public void setPdc_sum_in_all(int pdc_sum_in_all) {
        this.pdc_sum_in_all = pdc_sum_in_all;
    }

    public int getPdc_in_article() {
        return pdc_in_article;
    }

    public void setPdc_in_article(int pdc_in_article) {
        this.pdc_in_article = pdc_in_article;
    }

    public int getPdc_priority() {
        return pdc_priority;
    }

    public void setPdc_priority(int pdc_priority) {
        this.pdc_priority = pdc_priority;
    }
}
