package city;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class CityDBServiceImpl implements ICityDBService {

    private List<City> loadAllCitiesFromCsv(String path) {
        BufferedReader bufferedReader = null;
        List<City> cityList;
        try {
            bufferedReader = new BufferedReader(new FileReader(path));
        } catch (FileNotFoundException e) {
            System.out.println("Problem with reading entry data file");
            e.printStackTrace();
        }

        String line;
        cityList = new ArrayList<>();

        try {
            while ((line = bufferedReader.readLine()) != null) {
                String[] sI = line.split(",");
                String code = sI[0];
                String cityName = sI[1];
                City city = new City(code,cityName);
                cityList.add(city);
            }
        } catch (IOException e) {
            System.out.println("Problem with reading entry data line by line");
            e.printStackTrace();
        }
        return cityList;
    }

    private void addCitiesToDB(List<City> cities) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            for(City city:cities) {
                String queryInsertCity = "INSERT INTO city (postalcode,cityname) " + "VALUES (?,?) ";
                preparedStatement = connection.prepareStatement(queryInsertCity);
                preparedStatement.setString(1, city.getPostalCode());
                preparedStatement.setString(2, city.getCityName());
                preparedStatement.executeUpdate();
            }

        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public String getCityName(String postalCode) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        City city = new City();
        try {

                String querySelectCity = "SELECT * FROM city WHERE (postalcode) = (?) ";
                preparedStatement = connection.prepareStatement(querySelectCity);
                preparedStatement.setString(1, postalCode);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    city.setPostalCode(resultSet.getString("postalcode"));
                    city.setCityName(resultSet.getString("cityname"));
                }
            return city.getCityName();

        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }


}
