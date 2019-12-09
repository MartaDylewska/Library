package gui.book;

import book.*;

import javax.swing.*;
import java.awt.*;

public class BookEditPanel extends JPanel {

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField publisher, genre, language;
    private JTextArea title;
    private JLabel firstNameLabel, lastNameLabel;
    private JTextField firstName, lastName;
    private JLabel alleyLabel, bookstandLabel, shelfLabel;
    private JComboBox alley, bookstand, shelf;
    private JButton confirm, back;
    private JLabel result;
    private int fieldLength = 200;

    private IBook bookService = new BookService();
    private int bookIdToEdit;

    BookEditPanel(BookGetPanel bookGetPanel) {

        this.bookIdToEdit = bookGetPanel.getBookIdToEdit();
        Book book = bookService.getBook(bookIdToEdit);

        setLayout(null);

        createComps(book);
        addComp();
        action(book);

    }

    private void createComps(Book book) {

        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(20, 20, 100, 30);

        title = new JTextArea();
        title.setBounds(150, 20, fieldLength, 70);
        title.setBorder(BorderFactory.createLineBorder(Color.black));
        title.setLineWrap(true);
        title.setText(book.getTitle());

        firstNameLabel = new JLabel("Imię autora:");
        firstNameLabel.setBounds(20, 100, 100, 30);

        firstName = new JTextField();
        firstName.setBounds(150, 100, fieldLength, 30);
        firstName.setText(book.getAuthor().getFirstName());

        lastNameLabel = new JLabel("Nazwisko autora: ");
        lastNameLabel.setBounds(20, 140, 120, 30);

        lastName = new JTextField();
        lastName.setBounds(150, 140, fieldLength, 30);
        lastName.setText(book.getAuthor().getLastName());

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(20, 180, 100, 30);

        publisher = new JTextField();
        publisher.setBounds(150, 180, fieldLength, 30);
        publisher.setText(book.getPublisher());

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(20, 220, 100, 30);

        genre = new JTextField();
        genre.setBounds(150, 220, fieldLength, 30);
        genre.setText(book.getGenre());

        languageLabel = new JLabel("Język:");
        languageLabel.setBounds(20, 260, 100, 30);

        language = new JTextField();
        language.setBounds(150, 260, fieldLength, 30);
        language.setText(book.getLanguage());

        alleyLabel = new JLabel("Alejka:");
        alleyLabel.setBounds(20, 300, 55,30);

        alley = new JComboBox(new String[]{"-", "A", "B", "C", "D", "E"});
        alley.setBounds(75, 300, 55, 30);
        String alleyBook = book.getBookshelf().getAlley();
        alley.setSelectedItem(alleyBook);

        bookstandLabel = new JLabel("regał: ");
        bookstandLabel.setBounds(135,300,55,30);

        bookstand = new JComboBox(new String[]{"-", "a", "b", "c", "d", "e"});
        bookstand.setBounds(185,300,55,30);
        String bookstandBook = book.getBookshelf().getBookstand();
        bookstand.setSelectedItem(bookstandBook);

        shelfLabel = new JLabel("półka:");
        shelfLabel.setBounds(245,300,55,30);

        shelf = new JComboBox(new String[]{"-", "1", "2", "3", "4", "5"});
        shelf.setBounds(295,300,55,30);
        String shelfBook = String.valueOf(book.getBookshelf().getShelf());
        shelf.setSelectedItem(shelfBook);

        confirm = new JButton("Zatwierdź");
        confirm.setBounds(400, 20, 200, 50);

        back = new JButton("Cofnij");
        back.setBounds(400, 240, 200, 50);

        result = new JLabel();
        result.setBounds(20, 300, 460, 100);
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
        add(alleyLabel);
        add(alley);
        add(bookstandLabel);
        add(bookstand);
        add(shelfLabel);
        add(shelf);
        add(confirm);
        add(back);
        add(result);
    }

    private void action(Book book) {

        confirm.addActionListener(e -> {
            if(check()) {
                if(!(firstName.getText().equals(book.getAuthor().getFirstName())) || !(lastName.getText().equals(book.getAuthor().getLastName())))
                    bookService.editBook(bookIdToEdit, firstName.getText(), lastName.getText());
                if(!(alley.getSelectedItem().equals(book.getBookshelf().getAlley())) || !(bookstand.getSelectedItem().equals(book.getBookshelf().getBookstand())) || !(shelf.getSelectedItem().equals(String.valueOf(book.getBookshelf().getShelf()))))
                    bookService.editBook(bookIdToEdit, alley.getSelectedItem().toString(), bookstand.getSelectedItem().toString(), Integer.parseInt(shelf.getSelectedItem().toString()));
                if(!(title.getText().equals(book.getTitle())) || !(publisher.getText().equals(book.getPublisher())) || !(genre.getText().equals(book.getGenre())) || !(language.getText().equals(book.getLanguage())))
                    bookService.editBook(bookIdToEdit, title.getText(), publisher.getText(), genre.getText(), language.getText());

                result.setText(bookService.getMessage());
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });
    }

    private boolean check(){

        boolean titleCheck = title.getText().length() > 0 && title.getText().length() < 60;
        boolean firstNameCheck = firstName.getText().length() > 0 && firstName.getText().length() < 20;
        boolean lastNameCheck = lastName.getText().length() > 0 && lastName.getText().length() < 20;
        boolean publisherCheck = publisher.getText().length() > 0 && publisher.getText().length() < 20;
        boolean genreCheck = genre.getText().length() > 0 && genre.getText().length() < 20;
        boolean languageCheck = language.getText().length() > 0 && language.getText().length() < 20;
        boolean alleyCheck = alley.getSelectedIndex() != 0;
        boolean bookstandCheck = bookstand.getSelectedIndex() != 0;
        boolean shelfCheck = shelf.getSelectedIndex() != 0;

        return titleCheck && firstNameCheck && lastNameCheck && publisherCheck &&
                genreCheck && languageCheck && alleyCheck && bookstandCheck && shelfCheck;
    }

    public JButton getBack() {
        return back;
    }
}

