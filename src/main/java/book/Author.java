package book;

class Author {

    private String firstName, lastName;

    Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private String getFirstName() {
        return firstName;
    }

    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private String getLastName() {
        return lastName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
