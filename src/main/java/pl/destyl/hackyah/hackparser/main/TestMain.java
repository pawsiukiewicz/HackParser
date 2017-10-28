package pl.destyl.hackyah.hackparser.main;

import pl.destyl.hackyah.hackparser.db.dao.ArticleDao;
import pl.destyl.hackyah.hackparser.db.dao.CategoryDictDao;
import pl.destyl.hackyah.hackparser.db.dao.DictionaryDao;
import pl.destyl.hackyah.hackparser.db.dao.ProductsDictDao;
import pl.destyl.hackyah.hackparser.db.dto.Article;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static pl.destyl.hackyah.hackparser.parser.ArticleParser.parse;

/**
 * Created by destyl on 2017-10-28.
 */
public class TestMain {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        DictionaryDao dictionaryDao = new DictionaryDao();
        ProductsDictDao productsDictDao = new ProductsDictDao();
        CategoryDictDao categoryDictDao = new CategoryDictDao();

        List<Article> articles = articleDao.getNotParsedArticle();
        for (Article article : articles) {
            long time = System.currentTimeMillis();
            System.out.println(article.toString());

            Collection<Dictionary> words = parse(article);
            long timeParse = System.currentTimeMillis() - time;

            // kazde slowo jakie wystepuje w artykule musi by dodane do listy words.

            // na konie wynik wysylamy do bazy: przesylamy liste slow, kategorie artykulu, oraz jakiego produktu dotyczy.
            dictionaryDao.updateListOfWord(words);
            if (article.getArc_prd() >0) {
                productsDictDao.updateListOfWord(words, article.getArc_prd());
            }
            if (article.getArc_cat() > 0) {
                categoryDictDao.updateListOfWord(words, article.getArc_cat());
            }
            articleDao.setParsedArticle(article);
            long fullArticleTime = System.currentTimeMillis() - time;
            System.out.println("ParseTime =" +timeParse +" parse+setBase="+fullArticleTime);
        }
        articleDao.close();
    }
}
