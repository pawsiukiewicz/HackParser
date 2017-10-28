package pl.destyl.hackyah.hackparser.db.dao;

import java.sql.*;

/**
 * Created by destyl on 2017-10-28.
 */
public class AbstractDao {

    private DBConnection conn = DBConnection.getInstance();


    protected Connection getConnection() {
        return conn.getConnection();
    }

    protected Statement getStatement() {
        try {
            return conn.getConnection().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected ResultSet executeQuery(String query) {
        try {
            Statement statement = getConnection().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void executeUpdate(String query, int id) {
        try {
            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        conn.close();
    }
}
