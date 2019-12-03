package book;

import javax.swing.*;

class AddBookPanel extends JPanel {

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField title, publisher, genre, language;
    private JLabel firstNameLabel, lastNameLabel;
    private JTextField firstName, lastName;
    private JButton confirm, show, remove, edit;
    private JLabel result;

    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();

    AddBookPanel() {

        setLayout(null);

        createComps();
        addComp();
        action();

    }

    private void createComps() {
        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(20, 20, 200, 30);

        title = new JTextField();
        title.setBounds(20, 50, 200, 30);

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(20, 80, 200, 30);

        publisher = new JTextField();
        publisher.setBounds(20, 110, 200, 30);

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(20, 140, 200, 30);

        genre = new JTextField();
        genre.setBounds(20, 170, 200, 30);

        languageLabel = new JLabel("język");
        languageLabel.setBounds(20, 200, 200, 30);

        language = new JTextField();
        language.setBounds(20, 230, 200, 30);

        firstNameLabel = new JLabel("Imię:");
        firstNameLabel.setBounds(20, 260, 200, 30);

        firstName = new JTextField();
        firstName.setBounds(20, 290, 200, 30);

        lastNameLabel = new JLabel("Nazwisko: ");
        lastNameLabel.setBounds(20, 320, 200, 30);

        lastName = new JTextField();
        lastName.setBounds(20, 350, 200, 30);

        confirm = new JButton("Dodaj");
        confirm.setBounds(250, 20, 200, 30);

        show = new JButton("Pokaż");
        show.setBounds(250, 50, 200, 30);

        remove = new JButton("Usuń");
        remove.setBounds(250,80,200,30);

        edit = new JButton("Edytuj");
        edit.setBounds(250,110,200,30);

        result = new JLabel();
        result.setBounds(20, 380, 460, 100);
    }

    private void addComp() {
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
        add(remove);
        add(edit);
        add(result);
    }

    private void action() {

        confirm.addActionListener(e -> {
            authorService.addAuthor(firstName.getText(), lastName.getText());
            bookService.addBook(title.getText(), genre.getText(), publisher.getText(), language.getText(), firstName.getText(), lastName.getText());
            result.setText(bookService.getMessage());
        });

        show.addActionListener(e -> {
            result.setText("<html>" + bookService.getAllBooks().toString() + "</html>");
        });

        remove.addActionListener(e -> {
            bookService.removeBook(firstName.getText(), lastName.getText());
            result.setText(bookService.getMessage());
        });

        edit.addActionListener(e -> {
            authorService.editAuthor(1, firstName.getText(), lastName.getText());
            result.setText(bookService.getMessage());
        });

    }
}
