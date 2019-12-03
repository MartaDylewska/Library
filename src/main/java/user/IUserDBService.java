package user;

import java.util.List;

public interface IUserDBService {

    void addUserInDB(User user);
    void deleteUserFromDB(int idUser);
    User readUserFromDB(int idUser);
    List<User> getAllUsersFromDB();
    void updateUserInDB(int idUser, String firstName, String lastName, String email, String password, String address, int cardNumber);

}
