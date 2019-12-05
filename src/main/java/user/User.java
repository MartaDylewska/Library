package user;

import java.util.Objects;

public class User {

    private int idUser;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private int cardNumber;
    private String streetBuilding;
    private String postalCode;

    public User(){};

    public User(int idUser, String firstName, String lastName, String email, String password, String streetBuilding,String postalCode, int cardNumber) {
        this.idUser = idUser;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        this.streetBuilding=streetBuilding;
        this.postalCode = postalCode;
    }

    public User(String firstName, String lastName, String email, String password, String streetBuilding, String postalCode){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.streetBuilding=streetBuilding;
        this.postalCode = postalCode;
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

    public String getStreetBuilding() {
        return streetBuilding;
    }

    public void setStreetBuilding(String streetBuilding) {
        this.streetBuilding = streetBuilding;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
                Objects.equals(getStreetBuilding(), user.getStreetBuilding()) &&
                Objects.equals(getPostalCode(), user.getPostalCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdUser(), getFirstName(), getLastName(), getEmail(), getPassword(), getCardNumber(), getStreetBuilding(), getPostalCode());
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + cardNumber;
    }
}
