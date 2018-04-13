package main;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection connect(){

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            return null;
        }

        Connection connection = null;

        try {

            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "HR", "admin");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }

        if (connection == null) {
            return null;
        }
        return connection;
    }

}

