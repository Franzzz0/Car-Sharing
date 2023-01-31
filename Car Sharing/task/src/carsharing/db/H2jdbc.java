package carsharing.db;

import carsharing.TableUnit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2jdbc {
    private final String JDBC_DRIVER;
    private final String DB_URL;

    public H2jdbc(String fileName) {
        this.JDBC_DRIVER = "org.h2.Driver";
        this.DB_URL = "jdbc:h2:./src/carsharing/db/" + fileName;
    }

    public List<TableUnit> getList(String s) {
        List<TableUnit> list = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);
            statement = connection.createStatement();
            String sql = String.format("SELECT * FROM %s", s);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");

                try {
                    int referenceID = resultSet.getInt(3);
                    list.add(new TableUnit(id, name, referenceID));
                } catch (SQLException e) {
                    list.add(new TableUnit(id, name));
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return list;
    }

    public void executeTask(String... statements) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL);

            statement = connection.createStatement();
            for (String sql : statements) {
                statement.executeUpdate(sql);
            }

            statement.close();
            connection.close();
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
