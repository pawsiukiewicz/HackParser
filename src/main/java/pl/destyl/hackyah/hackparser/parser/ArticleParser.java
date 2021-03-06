package pl.destyl.hackyah.hackparser.parser;

import morfologik.stemming.polish.PolishStemmer;
import pl.destyl.hackyah.hackparser.db.dto.Article;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.util.*;

public class ArticleParser {
    private static String delimiters = "\\s+|-|\\.|\\?|!";

    public static Set<Dictionary> parse(Article article){
        Map<String, Integer> countedWords = parseArticle(article.getArc_text());
        int articleSize = countedWords.size();
        Set<Dictionary> words = new LinkedHashSet<>(articleSize);
        countedWords.forEach((word, amount) -> {
            Dictionary dictionary = new Dictionary();
            dictionary.setDic_word(word);
            dictionary.setDic_count(amount);
            dictionary.setDic_in_article(1);
            dictionary.setDic_sum_in_all(articleSize);
            words.add(dictionary);
        });
        return words;
    }

    private static Map<String,Integer> parseArticle(String article) {
        String[] split = article.split(delimiters);
        String[] stemmedSplit = stemWords(split);

        Map<String, Integer> hm = new HashMap<>();

        for (String aSplit : stemmedSplit) {
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

    private static String[] stemWords(String[] words) {
        PolishStemmer stemmer = new PolishStemmer();
        String[] stemmedWords = new String[words.length];
        for(int i=0; i<words.length; ++i) {
            stemmedWords[i] = stemmer.lookup(words[i].toLowerCase()).get(0).getStem().toString();
        }
        return stemmedWords;
    }

    //Junit wersja lajt
    public static void main(String[] args) {
        String[] testwords = {"Bank", "banki", "banków", "bankowość", "bankowości"};
        for(String s : stemWords(testwords)) System.out.println(s);
    }
}
