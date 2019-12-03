package reader;

import user.User;

public class Reader extends User {

    public Reader() {
    }

    public Reader(int idUser, String firstName, String lastName, String email, String password, String address, int cardNumber) {
        super(idUser, firstName, lastName, email, password, address, cardNumber);
    }

    public Reader(String firstName, String lastName, String email, String password, String address) {
        super(firstName, lastName, email, password, address);
    }
}
