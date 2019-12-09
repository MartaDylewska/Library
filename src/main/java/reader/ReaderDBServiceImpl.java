package reader;

<<<<<<< HEAD
import librarian.Librarian;
=======
>>>>>>> books
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class ReaderDBServiceImpl implements IReaderDBService {

    @Override
    public void addReaderInDB(int idUser) {

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertReader = "INSERT INTO reader (userid) " + "VALUES (?) ";
            preparedStatement = connection.prepareStatement(queryInsertReader);
            preparedStatement.setInt(1,idUser);
            preparedStatement.executeUpdate();
            System.out.println("New reader was added to database");

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
    public void deleteReaderFromDB(int idUser) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryDeleteReader = "DELETE FROM reader WHERE userid = ? ";
            preparedStatement = connection.prepareStatement(queryDeleteReader);
            preparedStatement.setInt(1,idUser);
            preparedStatement.execute();
            System.out.println("Reader was deleted");
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
    public Reader readReaderFromDB(int idUser) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryReadReader = "SELECT * FROM reader WHERE (userid) = (?) ";
            preparedStatement = connection.prepareStatement(queryReadReader);
            preparedStatement.setInt(1,idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            Reader reader = new Reader();
            while (resultSet.next()){
                reader.setIdReader(resultSet.getInt("idreader"));
                reader.setIdUser(resultSet.getInt("userid"));
               }
            return reader;
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
    public List<Reader> getAllReadersFromDB() {
        IUserDBService userDBService = new UserDBServiceImpl();
        List<User> userList = userDBService.getAllUsersFromDB();
        List<Reader> readerList = new ArrayList<>();

        try {
            for(User u: userList){
                Reader reader = readReaderFromDB(u.getIdUser());
                if(reader.getIdReader()!=0) {
                    reader.setIdUser(u.getIdUser());
                    reader.setFirstName(u.getFirstName());
                    reader.setLastName(u.getLastName());
                    reader.setCardNumber(u.getCardNumber());
                    reader.setEmail(u.getEmail());
                    reader.setPassword(u.getPassword());
                    reader.setPostalCode(u.getPostalCode());
                    reader.setStreetBuilding(u.getStreetBuilding());
                    readerList.add(reader);
                }
            }
            return readerList;
        }
        catch (Exception e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw new RuntimeException("Error during invoke SQL query");
        }
    }


    @Override
    public void chooseBook() {

    }

    @Override
    public void makeReservation() {

    }

    @Override
    public void seeListOfBorrows() {

    }
}
