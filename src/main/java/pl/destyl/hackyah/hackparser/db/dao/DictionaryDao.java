package pl.destyl.hackyah.hackparser.db.dao;

import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by destyl on 2017-10-28.
 */
public class DictionaryDao extends AbstractDao {
    private static final String SELECT_BY_WORD = "SELECT * FROM dictionary WHERE dic_word = ?";

    private static final String INSERT_WORD = "INSERT INTO dictionary (dic_word, dic_count, dic_sum_in_all, dic_in_article, dic_prioryty) VALUES (?,?,?,?,?)";

    private static final String UPDATE_DICTIONARY = "UPDATE dictionary SET dic_count = ?, dic_sum_in_all = ?, dic_in_article = ? WHERE dic_id = ?";

    public void updateListOfWord(Collection<Dictionary> words) {

        for (Dictionary word : words) {
            Dictionary wordFromDb = getWord(word.getDic_word());
            if (wordFromDb == null) {
                createWord(word);
                wordFromDb = getWord(word.getDic_word());
            }
            word.setDic_id(wordFromDb.getDic_id());
            sumCounter(wordFromDb, word);
            updateWordInDb(wordFromDb);
        }

    }


    public Dictionary getWord(String word) {
        ResultSet result = executeQuery(SELECT_BY_WORD, word);
        try {
            if (result.next()) {
                Dictionary dict = new Dictionary();
                dict.setDic_id(result.getInt("dic_id"));
                dict.setDic_word(result.getString("dic_word"));
                dict.setDic_count(result.getInt("dic_count"));
                dict.setDic_in_article(result.getInt("dic_in_article"));
                dict.setDic_prioryty(result.getInt("dic_prioryty"));
                dict.setDic_sum_in_all(result.getInt("dic_sum_in_all"));
                return dict;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createWord(Dictionary word) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_WORD);
            statement.setString(1, word.getDic_word());
            statement.setInt(2, 0);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setInt(5, 1);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Dictionary sumCounter(Dictionary wordFromDb, Dictionary word) {

        wordFromDb.setDic_count(wordFromDb.getDic_count() + word.getDic_count());
        wordFromDb.setDic_sum_in_all(wordFromDb.getDic_sum_in_all() + word.getDic_sum_in_all());
        wordFromDb.setDic_in_article(wordFromDb.getDic_in_article() + word.getDic_in_article());

        return wordFromDb;
    }

    private void updateWordInDb(Dictionary word) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(UPDATE_DICTIONARY);
            statement.setInt(1, word.getDic_count());
            statement.setInt(2, word.getDic_sum_in_all());
            statement.setInt(3, word.getDic_in_article());
            statement.setInt(4, word.getDic_id());

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
