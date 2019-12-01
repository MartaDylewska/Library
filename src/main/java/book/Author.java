package book;

import java.util.ArrayList;
import java.util.List;

class Author implements IAuthor{
    
    private String firstName, lastName;
    private int idAuthor;
    private static int _idAuthor = 0;
    private List<Author> authors = new ArrayList<>();

    Author(){}

    private Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        setIdAuthor();
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

    private int getIdAuthor() {
        return idAuthor;
    }

    private void setIdAuthor() {
        _idAuthor++;
        this.idAuthor = idAuthor + _idAuthor;
    }

    @Override
    public Author addAuthor(String firstName, String lastName) {
        Author author = new Author(firstName, lastName);
        boolean present = false;

        for (Author author1 : authors){
            if(firstName.equals(author1.getFirstName()) && lastName.equals(author1.getLastName()))
                present = true;
        }

        if(!present){
            authors.add(author);
        }

        return author;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public Author getAuthor(int id) {
        Author author = null;
        boolean present = false;

        for(Author author1 : authors){
            if(author1.getIdAuthor() == id){
                author = author1;
                present = true;
            }
        }
        if(!present)
            System.out.println("Nie ma takiego autora");

        return author;
    }

    @Override
    public Author getAuthor(String name) {
        Author author = null;
        boolean present = false;

        for(Author author1 : authors){
            if(author1.getFirstName().equals(name) || author1.getLastName().equals(name)){
                author = author1;
                present = true;
            }
        }
        if(!present)
            System.out.println("Nie ma takiego autora");

        return author;
    }

    @Override
    public void removeAuthor(int id) {
        Author author = getAuthor(id);
        authors.remove(author);
    }

    @Override
    public void removeAuthor(String name) {
        Author author = getAuthor(name);
        authors.remove(author);
    }

    @Override
    public String toString(){
        return firstName + " " + lastName;
    }

    @Override
    public void editAuthor(int id, String firstName, String lastName) {
        Author author = getAuthor(id);
        author.setFirstName(firstName);
        author.setLastName(lastName);
    }

    @Override
    public void editAuthor(String name, String firstName, String lastName) {
        Author author = getAuthor(name);
        author.setFirstName(firstName);
        author.setLastName(lastName);
    }
}
