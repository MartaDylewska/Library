package lending;

import event.Event;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class LendingDBServiceImpl implements ILendingDBService {

    @Override
    public void addLending(Lending lending) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO lending (book_id, creationdate, duedate, reader_id) " + "VALUES (?,?,?,?) ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,lending.getBook_id());
            Date sqlFormatDate = java.sql.Date.valueOf(lending.getDateCreation());
            preparedStatement.setDate(2,sqlFormatDate);
            Date sqlFormatDate2 = java.sql.Date.valueOf(lending.getDateDue());
            preparedStatement.setDate(3,sqlFormatDate2);
            preparedStatement.setInt(4,lending.getReader_id());
            preparedStatement.executeUpdate();
            System.out.println("New lending was added to database");
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
    public void deleteLending(int idLending) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM lending WHERE idlending = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idLending);
            preparedStatement.execute();
            System.out.println("Lending was deleted");
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
    public List<Lending> showAllLendings() {
        return null;
    }

    @Override
    public List<Lending> showLendingsForUser(int cardNr) {
        IUserDBService userDBService = new UserDBServiceImpl();
        User user = userDBService.readUserFromDB(cardNr);
        IReaderDBService readerDBService = new ReaderDBServiceImpl();
        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        int readerId = reader.getIdReader();
        List<Lending> lendingList = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "Select * FROM lending WHERE reader_id = (?)" ;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,readerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Lending lending = new Lending();
                lending.setReader_id(resultSet.getInt("reader_id"));
                lending.setDateDue(resultSet.getDate("duedate").toLocalDate());
                lending.setDateCreation(resultSet.getDate("creationdate").toLocalDate());
                lending.setBook_id(resultSet.getInt("book_id"));
                lending.setIdLending(resultSet.getInt("idlending"));

                lendingList.add(lending);
            }
            return lendingList;
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
