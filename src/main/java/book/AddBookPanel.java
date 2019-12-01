package book;

import javax.swing.*;
import java.sql.*;

class AddBookPanel extends JPanel {

    private static final String dbHost = "packy.db.elephantsql.com";
    private static final String dbPort = "5432";
    private static final String dbUser = "dxdqdjgq";
    private static final String dbPass = "k4T24isJOkQ8D4Kndq3yr8am_GjQd3RJ";
    private static final String dbName = "dxdqdjgq";

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField title, publisher, genre, language;
    private JLabel firstNameLabel, lastNameLabel;
    private JTextField firstName, lastName;
    private JButton confirm, show;
    private JLabel result;

    private IAuthor author = new Author();
    private IBook book = new Book();

    AddBookPanel(){

        setLayout(null);

        createComps();
        addComp();
        action();

    }
    private void createComps(){
        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(20,20,200,30);

        title = new JTextField();
        title.setBounds(20,50,200,30);

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(20,80,200,30);

        publisher = new JTextField();
        publisher.setBounds(20,110,200,30);

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(20,140,200,30);

        genre = new JTextField();
        genre.setBounds(20,170,200,30);

        languageLabel = new JLabel("język");
        languageLabel.setBounds(20,200,200,30);

        language = new JTextField();
        language.setBounds(20,230,200,30);

        firstNameLabel = new JLabel("Imię:");
        firstNameLabel.setBounds(20,260,200,30);

        firstName = new JTextField();
        firstName.setBounds(20,290,200,30);

        lastNameLabel = new JLabel("Nazwisko: ");
        lastNameLabel.setBounds(20,320,200,30);

        lastName = new JTextField();
        lastName.setBounds(20,350,200,30);

        confirm = new JButton("Dodaj");
        confirm.setBounds(250,20,200,30);

        show = new JButton("Pokaż");
        show.setBounds(250,50,200,30);

        result = new JLabel();
        result.setBounds(20,380,460,100);
    }

    private void addComp(){
        add(titleLabel);
        add(title);
        add(publisherLabel);
        add(publisher);
        add(genreLabel);
        add(genre);
        add(languageLabel);
        add(language);
        add(firstNameLabel);
        add(firstName);
        add(lastNameLabel);
        add(lastName);
        add(confirm);
        add(show);
        add(result);
    }

    private void action(){

        confirm.addActionListener(e -> {
            author.addAuthor(firstName.getText(), lastName.getText());
            book.addBook(title.getText(), genre.getText(), publisher.getText(), language.getText(), firstName.getText(), lastName.getText());
        });

        show.addActionListener(e -> {
            result.setText(book.toString());
//            result.setText("<html>" + author.getAuthors() + "\n" +
//                    book.getAllBooks().toString() + "</html>");
        });
    }


    static Connection connect(){

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("PostgreSQL DataSource unable to load PostgreSQL JDBC Driver");
        }

        Connection conn = null;
        try{
            String url = "jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName;
            conn = DriverManager.getConnection(url, dbUser, dbPass);
            System.out.println("Connected.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return conn;
    }
}
