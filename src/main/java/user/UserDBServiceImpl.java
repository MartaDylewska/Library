package user;

import card.Card;
import card.CardDBServiceImpl;
import card.ICardDBService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class UserDBServiceImpl implements IUserDBService {
    @Override
    public void addUserInDB(User user) {
        //aby stworzyć użytkownika, trzeba stworzyć nową kartę
        ICardDBService cardDBService = new CardDBServiceImpl();
        cardDBService.addCardInDB();

        Card cardForNewUser = cardDBService.readLastCardFromDB();

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertUser = "INSERT INTO public.\"user\" (firstname, lastname, cardid,email,pass,address) " + "VALUES (?,?,?,?,?,?) ";
            preparedStatement = connection.prepareStatement(queryInsertUser);
            preparedStatement.setString(1,user.getFirstName());
            preparedStatement.setString(2,user.getLastName());
            preparedStatement.setInt(3,cardForNewUser.getIdCard());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5,user.getPassword());
            preparedStatement.setString(6,user.getAddress());
            preparedStatement.executeUpdate();
            System.out.println("New user was added to database");

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
    public void deleteUserFromDB(int idUser) {

    }

    @Override
    public User readUserFromDB(int idUser) {
        return null;
    }

    @Override
    public List<User> getAllUsersFromDB() {
        return null;
    }

    @Override
    public void updateUserInDB(int idUser, String firstName, String lastName, String email, String password, String address, int cardNumber) {

    }
}
