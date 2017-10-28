package pl.destyl.hackyah.hackparser.db.dao;

import pl.destyl.hackyah.hackparser.db.dto.CategoryDict;
import pl.destyl.hackyah.hackparser.db.dto.Dictionary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by destyl on 2017-10-28.
 */
public class CategoryDictDao extends AbstractDao {
    private static final String SELECT_BY_WORD_CAT = "SELECT * FROM category_dict WHERE cdt_dic_id = ? AND cdt_cat_id = ?";

    private static final String INSERT_WORD_CAT = "INSERT INTO category_dict (cdt_dic_id, cdt_cat_id, cdt_count, cdt_sum_in_all, cdt_in_article) VALUES (?,?,?,?,?)";

    private static final String UPDATE_PRODUCTS_DICT = "UPDATE category_dict SET cdt_count = ?, cdt_sum_in_all = ?, cdt_in_article = ? WHERE cdt_dic_id = ? AND cdt_cat_id = ?";

    public void updateListOfWord(List<Dictionary> words, int catId) {
        for (Dictionary word : words) {
            CategoryDict wordFromDb = getWord(word.getDic_id(), catId);
            if (wordFromDb == null) {
                createWord(word.getDic_id(), catId);
                wordFromDb = getWord(word.getDic_id(), catId);
            }
            sumCounter(wordFromDb, word);
            updateWordInDb(wordFromDb);


        }
    }

    public CategoryDict getWord(int dicId, int catId) {
        ResultSet result = executeQuery(SELECT_BY_WORD_CAT, dicId, catId);
        try {
            if (result.next()) {
                CategoryDict prdDic = new CategoryDict();
                prdDic.setCdt_dic_id(result.getInt("cdt_dic_id"));
                prdDic.setCdt_prd_id(result.getInt("cdt_cat_id"));
                prdDic.setCdt_count(result.getInt("cdt_count"));
                prdDic.setCdt_in_article(result.getInt("cdt_in_article"));
                prdDic.setCdt_sum_in_all(result.getInt("cdt_sum_in_all"));
                return prdDic;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createWord(int dicId, int CdtId) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_WORD_CAT);
            statement.setInt(1, dicId);
            statement.setInt(2, CdtId);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private CategoryDict sumCounter(CategoryDict wordFromDb, Dictionary word) {

        wordFromDb.setCdt_count(wordFromDb.getCdt_count() + word.getDic_count());
        wordFromDb.setCdt_sum_in_all(wordFromDb.getCdt_sum_in_all() + word.getDic_sum_in_all());
        wordFromDb.setCdt_in_article(wordFromDb.getCdt_in_article() + word.getDic_in_article());

        return wordFromDb;
    }

    private void updateWordInDb(CategoryDict word) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(UPDATE_PRODUCTS_DICT);
            statement.setInt(1, word.getCdt_count());
            statement.setInt(2, word.getCdt_sum_in_all());
            statement.setInt(3, word.getCdt_in_article());
            statement.setInt(4, word.getCdt_dic_id());
            statement.setInt(5, word.getCdt_prd_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
