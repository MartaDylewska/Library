package admin;

import java.util.List;

public interface IAdminDBService {
    void addAdmin(Admin admin);
    void deleteAdminFromDB(int idUser);
    Admin readAdminFromDB(int idCard);
    List<Admin> getAllAdminsFromDB();
    void updateAdminInDB(int userId, String salary, boolean isFullTime);
}
