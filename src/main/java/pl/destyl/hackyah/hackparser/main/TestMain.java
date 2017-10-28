package pl.destyl.hackyah.hackparser.main;

import pl.destyl.hackyah.hackparser.db.dao.ArticleDao;
import pl.destyl.hackyah.hackparser.db.dto.Article;

import java.util.List;

/**
 * Created by destyl_2 on 2017-10-28.
 */
public class TestMain {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();

        List<Article> articles = articleDao.getNotParsedArticle();
        for (Article article : articles) {
            System.out.println(article.toString());
        }
        articleDao.close();
    }
}
