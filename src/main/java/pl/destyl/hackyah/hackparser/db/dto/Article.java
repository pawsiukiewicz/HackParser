package pl.destyl.hackyah.hackparser.db.dto;

/**
 * Created by destyl on 2017-10-28.
 */
public class Article {

    private int art_id;

    private String arc_link;

    private int arc_parsed;

    private int arc_cat;

    private int arc_prd;
    private int arc_count_word;

    private int arc_prioryty;

    private String arc_text;

    public int getArt_id() {
        return art_id;
    }

    public void setArt_id(int art_id) {
        this.art_id = art_id;
    }

    public String getArc_link() {
        return arc_link;
    }

    public void setArc_link(String arc_link) {
        this.arc_link = arc_link;
    }

    public int getArc_parsed() {
        return arc_parsed;
    }

    public void setArc_parsed(int arc_parsed) {
        this.arc_parsed = arc_parsed;
    }

    public int getArc_cat() {
        return arc_cat;
    }

    public void setArc_cat(int arc_cat) {
        this.arc_cat = arc_cat;
    }

    public int getArc_prd() {
        return arc_prd;
    }

    public void setArc_prd(int arc_prd) {
        this.arc_prd = arc_prd;
    }

    public int getArc_count_word() {
        return arc_count_word;
    }

    public void setArc_count_word(int arc_count_word) {
        this.arc_count_word = arc_count_word;
    }

    public int getArc_prioryty() {
        return arc_prioryty;
    }

    public void setArc_prioryty(int arc_prioryty) {
        this.arc_prioryty = arc_prioryty;
    }

    public String getArc_text() {
        return arc_text;
    }

    public void setArc_text(String arc_text) {
        this.arc_text = arc_text;
    }

    public String toString() {
        String tresc= arc_text;
        if (tresc.length() > 100) {
            tresc = tresc.substring(0, 100);
        }
        return "id ="+art_id+" link="+arc_link+" tresc="+tresc;
    }
}
