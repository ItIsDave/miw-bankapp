package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBaccess {

    private Connection connection;
    private static final String SQL_EXCEPTION = "SQL Exception: ";
    private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_NAME = "couch";
    private static final String MAIN_USER = "userCouch";
    private static final String MAIN_USER_PASSWORD = "pwCouch";
    private static final String PREFIX_CONNECTION_URL = "jdbc:mysql://localhost:3306/";
    private static final String CONNECTION_SETTINGS = "?useSSL=false&allowPublicKeyRetrieval=true";

    public DBaccess() {
        super();
    }

    /**
     * Open database connection
     */
    public void openConnection() {
        String connectionURL = PREFIX_CONNECTION_URL + DATABASE_NAME + CONNECTION_SETTINGS;
        try {
            System.out.print("Load driver... ");
            Class.forName(MYSQL_DRIVER);				// Explicitly load the JDBC-driver.
            System.out.println("Driver geladen");

            System.out.println("Open connectie " + connectionURL + "... ");
            connection = DriverManager.getConnection(connectionURL, MAIN_USER, MAIN_USER_PASSWORD);
            System.out.println("OK, Connectie open");
        } catch (ClassNotFoundException driverProblem) {
            System.out.println("Driver not found");
        } catch (SQLException sqlproblem) {
            System.out.println(SQL_EXCEPTION + sqlproblem.getMessage());
        }
    }

    /**
     * Close database connection
     */
    public void closeConnection() {
        try {
            connection.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public Connection getConnection()  {
        return connection;
    }



}

