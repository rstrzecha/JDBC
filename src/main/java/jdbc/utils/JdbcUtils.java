package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private Connection connection;

    //Singleton
    private static JdbcUtils instance;       //instancja do pojedynczego obiektu

    public static JdbcUtils getInstance() {     //Lazy tworzymy tylko raz i dopiero przy wywołaniu
        if(instance == null) {
            instance = new JdbcUtils();
        }
        return instance;
    }

    private JdbcUtils() {
        String connectionString = "jdbc:mysql://localhost:3306/runcenter";
        Properties prop = new Properties();
        prop.put("password", "GoniaGv32My");
        prop.put("user", "root");
        prop.put("serverTimezone", "Europe/Warsaw");

        try {
            connection = DriverManager.getConnection(connectionString, prop);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}