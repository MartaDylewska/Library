package admin;

import user.User;

import java.time.LocalDate;
import java.util.Objects;

public class Admin extends User {
    private int idAdmin;
    private int userId;
    private String salary;
    private boolean isFullTime;

    public Admin(){}

    public Admin(int idAdmin, int userId, String salary, boolean isFullTime) {
        this.idAdmin = idAdmin;
        this.userId = userId;
        this.salary = salary;
        this.isFullTime = isFullTime;
    }

    public Admin(String firstName, String lastName, String email, String password, String streetBuilding, String postalCode, int idAdmin, int userId, String salary, boolean isFullTime) {
        super(firstName, lastName, email, password, streetBuilding, postalCode);
        this.idAdmin = idAdmin;
        this.userId = userId;
        this.salary = salary;
        this.isFullTime = isFullTime;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public boolean isFullTime() {
        return isFullTime;
    }

    public void setFullTime(boolean fullTime) {
        isFullTime = fullTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin)) return false;
        if (!super.equals(o)) return false;
        Admin admin = (Admin) o;
        return getIdAdmin() == admin.getIdAdmin() &&
                getUserId() == admin.getUserId() &&
                isFullTime() == admin.isFullTime() &&
                Objects.equals(getSalary(), admin.getSalary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getIdAdmin(), getUserId(), getSalary(), isFullTime());
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getCardNumber();
    }
}
