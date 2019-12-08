package librarian;

import user.User;

import java.time.LocalDate;

public class Librarian extends User {
    private int idLibrarian;
    private int userId;
    private String salary;
    private LocalDate employmentDate;

    public Librarian(){}

    public Librarian(int idLibrarian, int userId, String salary, LocalDate employmentDate){
        this.idLibrarian = idLibrarian;
        this.userId = userId;
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    public Librarian(String firstName, String lastName, String email, String password, String streetBuilding, String postalCode, int idLibrarian, int userId, String salary, LocalDate employmentDate) {
        super(firstName, lastName, email, password, streetBuilding, postalCode);
        this.idLibrarian = idLibrarian;
        this.userId = userId;
        this.salary = salary;
        this.employmentDate = employmentDate;
    }

    public int getIdLibrarian() {
        return idLibrarian;
    }

    public void setIdLibrarian(int idLibrarian) {
        this.idLibrarian = idLibrarian;
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

    public LocalDate getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getCardNumber();
    }
}
