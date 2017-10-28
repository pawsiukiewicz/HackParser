package pl.destyl.hackyah.hackparser.main;

import pl.destyl.hackyah.hackparser.db.dao.ArticleDao;
import pl.destyl.hackyah.hackparser.db.dao.DictionaryDao;
import pl.destyl.hackyah.hackparser.db.dto.Article;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.util.ArrayList;
import java.util.List;

import static pl.destyl.hackyah.hackparser.parser.ArticleParser.parse;

/**
 * Created by destyl_2 on 2017-10-28.
 */
public class TestMain {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        DictionaryDao dictionaryDao = new DictionaryDao();
        List<Dictionary> words = new ArrayList<>();

        List<Article> articles = articleDao.getNotParsedArticle();
        for (Article article : articles) {
            System.out.println(article.toString());

            words.addAll(parse(article));
            // kazde slowo jakie wystepuje w artykule musi by dodane do listy words.

            // na konie wynik wysylamy do bazy: przesylamy liste slow, kategorie artykulu, oraz jakiego produktu dotyczy.
            dictionaryDao.updateListOfWord(words, article.getArc_cat(), article.getArc_prd());
        }

        articleDao.close();
    }
}
