package user;

import java.util.List;

public interface IUserDBService {

    void addUserInDB(User user);
    void deleteUserFromDB(int idCard);
    User readUserFromDB(int idCard);
    User readUserFromDBById(int idUser);
    List<User> getAllUsersFromDB();
    void updateUserInDB(int idUser, String firstName, String lastName, String email, String password, String streetBuilding, String postalCode, int cardNumber);
    int readLastUserIdFromDB();
}
