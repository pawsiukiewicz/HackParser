package pl.destyl.hackyah.hackparser.db.dao;

import pl.destyl.hackyah.hackparser.db.dto.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by destyl on 2017-10-28.
 */
public class ArticleDao extends AbstractDao {

    private static final String SELECT_NOT_PARSED = "SELECT * FROM article WHERE arc_parsed = '0'";

    private static final String UPDATE_PARSED = "UPDATE article SET arc_parsed = 1 WHERE art_id = ?";

    public List<Article> getNotParsedArticle() {
        ResultSet result = executeQuery(SELECT_NOT_PARSED);
        List<Article> ret = new ArrayList<Article>();
        try {
            while (result.next()) {
                Article art = new Article();
                art.setArt_id(result.getInt("art_id"));
                art.setArc_text(result.getString("arc_text"));
                art.setArc_link(result.getString("arc_link"));
                art.setArc_cat(result.getInt("arc_cat"));
                art.setArc_prd(result.getInt("arc_prd"));
                art.setArc_count_word(result.getInt("arc_count_word"));
                art.setArc_parsed(result.getInt("Arc_parsed"));
                ret.add(art);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ret;
    }

    public void setParsedArticle(Article art) {
        executeUpdate(UPDATE_PARSED, art.getArt_id());
    }
}
