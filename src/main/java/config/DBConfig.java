package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConfig {

    private static final String dbHost = "packy.db.elephantsql.com";
    private static final String dbPort = "5432";
    private static final String dbUser = "dxdqdjgq";
    private static final String dbPass = "k4T24isJOkQ8D4Kndq3yr8am_GjQd3RJ";
    private static final String dbName = "dxdqdjgq";

    public static Connection initializeDataBaseConnection(){
/*        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }*/

        try{
            return DriverManager.getConnection("jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName,dbUser,dbPass);
        }
        catch (SQLException e){
            System.err.println("Server can't initialize database connection: \n" + e);
            throw  new RuntimeException("Server can't initialize database connection");
        }
    }

    public static void closeDBResources(Connection connection, Statement statement){
        try {
            if(statement != null)
                statement.close();

            if(connection != null)
                connection.close();
        }
        catch (SQLException e){
            System.err.println("Error while closing database resources: \n" + e);
            throw new RuntimeException("Error while closing database resources");
        }
    }
}
