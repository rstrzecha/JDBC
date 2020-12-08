package jdbc;

import jdbc.utils.JdbcUtils;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws SQLException {

//        insertTest();
//        deleteTest(1);
//        updateTest(1, "Zmieniona nazwa",99);
        printAllData();
    }

    private static void insertTest() throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();

        statement
                .executeUpdate("INSERT INTO runs (id, name, members_limit) VALUES (1, 'Stary bieg', 100)");
        statement.close();
    }

    private static void deleteTest(int id) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("DELETE FROM runs WHERE id=?");
        statement.setInt(1, id);

        statement.executeUpdate();
        statement.close();
    }

    private static void updateTest(int id, String name, int members_limit) throws SQLException {
        PreparedStatement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .prepareStatement("UPDATE runs SET name=?, members_limit=? WHERE id=?");
        statement.setString(1, name);
        statement.setInt(2, members_limit);
        statement.setInt(3, id);

        statement.executeUpdate();
        statement.close();
    }

    private static void printAllData() throws SQLException {
        Statement statement = JdbcUtils
                .getInstance()
                .getConnection()
                .createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM runs");
        while(resultSet.next()) {
            System.out.println(resultSet.getInt("id"));
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getInt("members_limit"));
            System.out.println("--------------------------------------");
        }
        statement.close();
    }
}