package user;

import java.util.Objects;

public class User {

    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int cardNumber;
    private String address;

    public User(){};

    public User(int idUser, String firstName, String lastName, String email, String password, String address, int cardNumber) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        this.address = address;
    }

    public User(String firstName, String lastName, String email, String password, String address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getIdUser() == user.getIdUser() &&
                getCardNumber() == user.getCardNumber() &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getAddress(), user.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getFirstName(), getLastName(), getEmail(), getPassword(), getCardNumber(), getAddress());
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", cardNumber=" + cardNumber +
                ", address='" + address + '\'' +
                '}';
    }
}
