package pl.destyl.hackyah.hackparser.parser;

import pl.destyl.hackyah.hackparser.db.dto.Article;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class ArticleParser {
    private static String delimiters = "\\s+|-|\\.|\\?|!";

    public static Set<Dictionary> parse(Article article){
        Map<String, Integer> countedWords = parseArticle(article.getArc_text());
        Set<Dictionary> words = new LinkedHashSet<>(countedWords.size());
        countedWords.forEach((word, amount) -> {
            Dictionary dictionary = new Dictionary();
            dictionary.setDic_word(word);
            dictionary.setDic_count(amount);
            dictionary.setDic_in_article(article.getArt_id());
            words.add(dictionary);
        });
        return words;
    }

    private static Map<String,Integer> parseArticle(String arcText) {
        String[] split = arcText.split(delimiters);
        Map<String, Integer> hm = new HashMap<>();

        for (String aSplit : split) {
            int x;
            if (hm.containsKey(aSplit)) {
                x = hm.get(aSplit);
                hm.put(aSplit, x + 1);
            } else {
                hm.put(aSplit, 1);
            }
        }
        return hm;
    }
}
