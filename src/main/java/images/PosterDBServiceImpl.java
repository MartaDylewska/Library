package images;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class PosterDBServiceImpl implements IPosterDBService {

    @Override
    public void addImage(String path) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        File file;
        FileInputStream fis;
        try {
            file = new File(path);
            fis = new FileInputStream(file);
            String queryInsertPoster = "INSERT INTO images VALUES (?, ?)";
            preparedStatement = connection.prepareStatement(queryInsertPoster);
            preparedStatement.setString(1, file.getName());
            preparedStatement.setBinaryStream(2, fis, file.length());
            preparedStatement.executeUpdate();
            fis.close();
            System.out.println("Poster added to db");
        } catch (FileNotFoundException ex) {
            System.err.println("Error during fetching picture for poster \n" + ex.getMessage());
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw new RuntimeException("Error during invoke SQL query");
        } catch (IOException e) {
            System.err.println("Error while closing the poster file: \n" + e.getMessage());
            e.printStackTrace();
        } finally {
            closeDBResources(connection, preparedStatement);
        }
    }

    @Override
    public Poster readImage(String imgName) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        Poster poster = new Poster();
        try {
            String queryReadPoster = "SELECT * FROM images WHERE imgname = ?";
            preparedStatement = connection.prepareStatement(queryReadPoster);
            preparedStatement.setString(1, imgName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                byte[] imgBytes = resultSet.getBytes(1);
                poster.setIdImg(resultSet.getInt("idimg"));
                poster.setImgBytes(imgBytes);
                poster.setImgName(imgName);
            }
            resultSet.close();
            return poster;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw new RuntimeException("Error during invoke SQL query");
        } finally {
            closeDBResources(connection, preparedStatement);
        }
    }

    @Override
    public Poster readImageById(int idPoster) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        Poster poster = new Poster();
        try {
            String queryReadPoster = "SELECT * FROM images WHERE idimg = ?";
            preparedStatement = connection.prepareStatement(queryReadPoster);
            preparedStatement.setInt(1, idPoster);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                byte[] imgBytes = resultSet.getBytes("img");
                poster.setIdImg(resultSet.getInt("idimg"));
                poster.setImgBytes(imgBytes);
                poster.setImgName(resultSet.getString("imgname"));
            }
            resultSet.close();
            return poster;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw new RuntimeException("Error during invoke SQL query");
        } finally {
            closeDBResources(connection, preparedStatement);
        }
    }

    @Override
    public Poster readLastImageFromDB() {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        Poster poster = new Poster();
        try {
            String queryReadPoster = "SELECT * FROM images order by idimg desc limit 1;";
            preparedStatement = connection.prepareStatement(queryReadPoster);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                byte[] imgBytes = resultSet.getBytes("img");
                poster.setIdImg(resultSet.getInt("idimg"));
                poster.setImgBytes(imgBytes);
                poster.setImgName(resultSet.getString("imgname"));
            }
            resultSet.close();
            return poster;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw new RuntimeException("Error during invoke SQL query");
        } finally {
            closeDBResources(connection, preparedStatement);
        }
    }
}
