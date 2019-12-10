package admin;

import librarian.Librarian;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class AdminDBServiceImpl implements IAdminDBService {

    @Override
    public void addAdmin(Admin admin) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertAdmin = "INSERT INTO admin (userid,salary,isfulltime) " + "VALUES (?,?,?) ";
            preparedStatement = connection.prepareStatement(queryInsertAdmin);
            preparedStatement.setInt(1,admin.getUserId());
            preparedStatement.setString(2,admin.getSalary());
            preparedStatement.setBoolean(3,admin.isFullTime());
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
    public void deleteAdminFromDB(int idUser) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryDeleteAdmin = "DELETE FROM admin WHERE userid = ? ";
            preparedStatement = connection.prepareStatement(queryDeleteAdmin);
            preparedStatement.setInt(1,idUser);
            preparedStatement.execute();
            System.out.println("Administrator was deleted");
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
    public Admin readAdminFromDB(int idCard) {
        IUserDBService userDBService = new UserDBServiceImpl();
        User user = userDBService.readUserFromDB(idCard);
        int userId = user.getIdUser();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            String queryReadAdmin = "SELECT * FROM admin WHERE (userid) = (?) ";
            preparedStatement = connection.prepareStatement(queryReadAdmin);
            preparedStatement.setInt(1,userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            Admin admin = new Admin();
            while (resultSet.next()){
                admin.setIdAdmin(resultSet.getInt("idadmin"));
                admin.setUserId(resultSet.getInt("userid"));
                admin.setSalary(resultSet.getString("salary"));
                admin.setFullTime(resultSet.getBoolean("isfulltime"));
                admin.setCardNumber(user.getCardNumber());
                admin.setEmail(user.getEmail());
                admin.setFirstName(user.getFirstName());
                admin.setLastName(user.getLastName());
                admin.setPassword(user.getPassword());
                admin.setPostalCode(user.getPostalCode());
                admin.setStreetBuilding(user.getStreetBuilding());
            }
            return admin;
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
    public List<Admin> getAllAdminsFromDB() {
        IUserDBService userDBService = new UserDBServiceImpl();
        //List<User> userList = userDBService.getAllUsersFromDB();
        List<Admin> adminList = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryReadLibrarians = "SELECT * FROM admin;" ;
            preparedStatement = connection.prepareStatement(queryReadLibrarians);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Admin admin = new Admin();
                admin.setUserId(resultSet.getInt("userid"));
                admin.setSalary(resultSet.getString("salary"));
                admin.setFullTime(resultSet.getBoolean("isfulltime"));
                admin.setIdAdmin(resultSet.getInt("idadmin"));
                User user = userDBService.readUserFromDBById(admin.getUserId());
                admin.setStreetBuilding(user.getStreetBuilding());
                admin.setPostalCode(user.getPostalCode());
                admin.setPassword(user.getPassword());
                admin.setLastName(user.getLastName());
                admin.setFirstName(user.getFirstName());
                admin.setEmail(user.getEmail());
                admin.setCardNumber(user.getCardNumber());

                adminList.add(admin);
            }
            return adminList;
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
    public void updateAdminInDB(int userId, String salary, boolean isFullTime) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryUpdateAdmin = "UPDATE admin SET (salary,isfulltime) = (?,?) " + "where userid = ?";
            preparedStatement = connection.prepareStatement(queryUpdateAdmin);
            preparedStatement.setString(1,salary);
            preparedStatement.setBoolean(2,isFullTime);
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
