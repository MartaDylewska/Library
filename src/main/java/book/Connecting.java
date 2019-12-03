package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

class Connecting {

    private static final String dbHost = "packy.db.elephantsql.com";
    private static final String dbPort = "5432";
    private static final String dbUser = "dxdqdjgq";
    private static final String dbPass = "k4T24isJOkQ8D4Kndq3yr8am_GjQd3RJ";
    private static final String dbName = "dxdqdjgq";

    Connecting(){}

    static {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }
    }

    Connection connectDB(){

        try{
            System.out.println("Connected");
            return DriverManager.getConnection("jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName, dbUser, dbPass);
        } catch (SQLException e){
            System.out.println(e.getMessage());
            throw new RuntimeException("Connection can't be initiallized");
        }
    }

    void closeDB(Connection connection, Statement statement){
        try{
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
        } catch (SQLException e){
            System.out.println("Problem during closing database");
            throw new RuntimeException("Error");
        }
    }
}
