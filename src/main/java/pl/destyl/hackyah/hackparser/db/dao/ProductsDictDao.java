package pl.destyl.hackyah.hackparser.db.dao;

import pl.destyl.hackyah.hackparser.db.dto.Dictionary;
import pl.destyl.hackyah.hackparser.db.dto.ProductsDict;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by destyl_2 on 2017-10-28.
 */
public class ProductsDictDao extends AbstractDao {

    private static final String SELECT_BY_WORD_CAT = "SELECT * FROM products_dict WHERE pdc_dic_id = ? AND pdc_prd_id = ?";

    private static final String INSERT_WORD_CAT = "INSERT INTO products_dict (pdc_dic_id, pdc_prd_id, pdc_count, pdc_sum_in_all, pdc_in_article) VALUES (?,?,?,?,?)";

    private static final String UPDATE_PRODUCTS_DICT = "UPDATE products_dict SET pdc_count = ?, pdc_sum_in_all = ?, pdc_in_article = ? WHERE pdc_dic_id = ? AND pdc_prd_id = ?";

    public void updateListOfWord(List<Dictionary> words, int prdId) {

        for (Dictionary word : words) {
            ProductsDict wordFromDb = getWord(word.getDic_id(), prdId);
            if (wordFromDb == null) {
                createWord(word.getDic_id(), prdId);
                wordFromDb = getWord(word.getDic_id(), prdId);
            }
            sumCounter(wordFromDb, word);
            updateWordInDb(wordFromDb);


        }

    }


    public ProductsDict getWord(int dicId, int catId) {
        ResultSet result = executeQuery(SELECT_BY_WORD_CAT, dicId, catId);
        try {
            if (result.next()) {
                ProductsDict prdDic = new ProductsDict();
                prdDic.setPdc_dic_id(result.getInt("pdc_dic_id"));
                prdDic.setPdc_prd_id(result.getInt("pdc_prd_id"));
                prdDic.setPdc_count(result.getInt("pdc_count"));
                prdDic.setPdc_in_article(result.getInt("pdc_in_article"));
                prdDic.setPdc_sum_in_all(result.getInt("pdc_sum_in_all"));
                return prdDic;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void createWord(int dicId, int pdcId) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(INSERT_WORD_CAT);
            statement.setInt(1, dicId);
            statement.setInt(2, pdcId);
            statement.setInt(3, 0);
            statement.setInt(4, 0);
            statement.setInt(5, 0);

            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ProductsDict sumCounter(ProductsDict wordFromDb, Dictionary word) {

        wordFromDb.setPdc_count(wordFromDb.getPdc_count() + word.getDic_count());
        wordFromDb.setPdc_sum_in_all(wordFromDb.getPdc_sum_in_all() + word.getDic_sum_in_all());
        wordFromDb.setPdc_in_article(wordFromDb.getPdc_in_article() + word.getDic_in_article());

        return wordFromDb;
    }

    private void updateWordInDb(ProductsDict word) {
        try {
            PreparedStatement statement = getConnection().prepareStatement(UPDATE_PRODUCTS_DICT);
            statement.setInt(1, word.getPdc_count());
            statement.setInt(2, word.getPdc_sum_in_all());
            statement.setInt(3, word.getPdc_in_article());
            statement.setInt(4, word.getPdc_dic_id());
            statement.setInt(5, word.getPdc_prd_id());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
