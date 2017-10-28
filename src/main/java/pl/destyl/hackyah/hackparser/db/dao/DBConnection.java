package pl.destyl.hackyah.hackparser.db.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by destyl on 2017-10-28.
 */
public class DBConnection {

    private static final String USERNAME = "destyl_hack";
    private static final String PASSWORD = "pal1nka#";
    private static final String STEROWNIK = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://mysql-486830.vipserv.org/destyl_hack";

    private Connection connection;

    private static String initialized = "NO";

    private static DBConnection instance;

    public static DBConnection getInstance() {
        synchronized (initialized) {
            if (instance == null) {
                instance = new DBConnection();
            }
        }
        return instance;
    }

    private DBConnection() {
        connection = getConnection();
        initialized = "YES";
    }


    public Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(STEROWNIK);
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Database connection established");
        } catch (Exception e) {
            System.err.println("Cannot connect to database server");
            e.printStackTrace();
        }
        return conn;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
