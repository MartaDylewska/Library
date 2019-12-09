package reader;

import user.User;

public class Reader extends User {

    private int idReader;
    private int idUser;

    public Reader(){}

/*    public Reader(int idReader, int idUser){
        this.idReader = idReader;
        this.idUser = idUser;
    }

    public Reader(int idUser){
        this.idUser = idUser;
    }

    public Reader(String firstName, String lastName, String email, String password, String streetBuilding, String postalCode, int idReader, int idUser) {
        super(firstName, lastName, email, password, streetBuilding, postalCode);
        this.idReader = idReader;
        this.idUser = idUser;
    }*/

    public int getIdReader() {return idReader; }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " " + getCardNumber();
    }
}

