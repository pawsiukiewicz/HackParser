package pl.destyl.hackyah.hackparser.main;

import pl.destyl.hackyah.hackparser.db.dao.ArticleDao;
import pl.destyl.hackyah.hackparser.db.dao.CategoryDictDao;
import pl.destyl.hackyah.hackparser.db.dao.DictionaryDao;
import pl.destyl.hackyah.hackparser.db.dao.ProductsDictDao;
import pl.destyl.hackyah.hackparser.db.dto.Article;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by destyl on 2017-10-28.
 */
public class TestMain {
    public static void main(String[] args) {
        ArticleDao articleDao = new ArticleDao();
        DictionaryDao dictionaryDao = new DictionaryDao();
        ProductsDictDao productsDictDao = new ProductsDictDao();
        CategoryDictDao categoryDictDao = new CategoryDictDao();

        List<Dictionary> words = new ArrayList<Dictionary>();

        List<Article> articles = articleDao.getNotParsedArticle();
        for (Article article : articles) {
            System.out.println(article.toString());

            // kazde slowo jakie wystepuje w artykule musi by dodane do listy words.

            // na konie wynik wysylamy do bazy: przesylamy liste slow, kategorie artykulu, oraz jakiego produktu dotyczy.
            dictionaryDao.updateListOfWord(words);
            if (article.getArc_prd() >0) {
                productsDictDao.updateListOfWord(words, article.getArc_prd());
            }
            if (article.getArc_cat() > 0) {
                categoryDictDao.updateListOfWord(words, article.getArc_cat());
            }
        }

        Dictionary word = new Dictionary();
        word.setDic_word("test");
        word.setDic_count(1);
        word.setDic_prioryty(0);
        word.setDic_sum_in_all(10);
        word.setDic_in_article(1);

        words.add(word);

        dictionaryDao.updateListOfWord(words);

        System.out.println(words.get(0));

        articleDao.close();
    }
}
