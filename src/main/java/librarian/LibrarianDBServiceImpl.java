package librarian;

import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class LibrarianDBServiceImpl implements ILibrarianDBService {
    @Override
    public void addLibrarianInDB(Librarian librarian) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertLibrarian = "INSERT INTO librarian (userid,salary,employmentdate) " + "VALUES (?,?,?) ";
            preparedStatement = connection.prepareStatement(queryInsertLibrarian);
            preparedStatement.setInt(1,librarian.getUserId());
            preparedStatement.setString(2,librarian.getSalary());
            Date sqlFormatDate = java.sql.Date.valueOf(librarian.getEmploymentDate());
            preparedStatement.setDate(3,sqlFormatDate);
            preparedStatement.executeUpdate();
            System.out.println("New librarian was added to database");

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
    public void deleteLibrarianFromDB(int idUser) {

    }

    @Override
    public Librarian readLibrarianFromDB(int idCard) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        IUserDBService userDBService = new UserDBServiceImpl();
        User user = userDBService.readUserFromDB(idCard);
        int userId = user.getIdUser();
        try {
            String queryReadLibrarian = "SELECT * FROM librarian WHERE (userid) = (?) ";
            preparedStatement = connection.prepareStatement(queryReadLibrarian);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Librarian librarian = new Librarian();
            while (resultSet.next()){
                librarian.setIdLibrarian(resultSet.getInt("idlibrarian"));
                librarian.setUserId(resultSet.getInt("userid"));
                librarian.setSalary(resultSet.getString("salary"));
                librarian.setEmploymentDate(resultSet.getDate("employmentdate").toLocalDate());
                librarian.setCardNumber(user.getCardNumber());
                librarian.setEmail(user.getEmail());
                librarian.setFirstName(user.getFirstName());
                librarian.setLastName(user.getLastName());
                librarian.setPassword(user.getPassword());
                librarian.setPostalCode(user.getPostalCode());
                librarian.setStreetBuilding(user.getStreetBuilding());
            }
            return librarian;
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
    public List<Librarian> getAllLibrariansFromDB() {
        return null;
    }

    @Override
    public void updateLibrarianInDB(int userId, String salary, LocalDate employmentDate) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryUpdateLibrarian = "UPDATE librarian SET (salary,employmentdate) = (?,?) " + "where userid = ?";
            preparedStatement = connection.prepareStatement(queryUpdateLibrarian);
            preparedStatement.setString(1,salary);
            Date sqlDate = java.sql.Date.valueOf(employmentDate);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,userId);
            preparedStatement.executeUpdate();
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
