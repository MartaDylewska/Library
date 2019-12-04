package reader;

import user.User;

public class Reader extends User {

    public Reader() {
    }

    public Reader(int idUser, String firstName, String lastName, String email, String password, String streetBuilding, String postalCode, int cardNumber) {
        super(idUser, firstName, lastName, email, password, streetBuilding, postalCode, cardNumber);
    }

    public Reader(String firstName, String lastName, String email, String password, String streetBuilding, String postalCode) {
        super(firstName, lastName, email, password, streetBuilding, postalCode);
    }
}
