package gui.book;

import book.AuthorService;
import book.BookService;
import book.IAuthor;
import book.IBook;

import javax.swing.*;
import java.awt.*;

public class BookAddPanel extends JPanel {

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField publisher, genre, language;
    private JTextArea title;
    private JLabel firstNameLabel, lastNameLabel;
    private JTextField firstName, lastName;
    private JLabel alleyLabel, bookstandLabel, shelfLabel;
    private JComboBox alley, bookstand, shelf;
    private JButton confirm, back;
    private JLabel result;
    private JRadioButton oneAuthor, moreAuthors;
    private ButtonGroup buttonGroup;

    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();

    BookAddPanel() {

        setLayout(null);

        createComps();
        addComp();
        action();

    }

    private void createComps() {

        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(20, 20, 100, 30);

        title = new JTextArea();
        title.setBounds(150, 20, 200, 70);
        title.setBorder(BorderFactory.createLineBorder(Color.black));
        title.setLineWrap(true);

        firstNameLabel = new JLabel("Imię autora:");
        firstNameLabel.setBounds(20, 100, 100, 30);

        firstName = new JTextField();
        firstName.setBounds(150, 100, 200, 30);

        lastNameLabel = new JLabel("Nazwisko autora: ");
        lastNameLabel.setBounds(20, 140, 120, 30);

        lastName = new JTextField();
        lastName.setBounds(150, 140, 200, 30);

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(20, 180, 100, 30);

        publisher = new JTextField();
        publisher.setBounds(150, 180, 200, 30);

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(20, 220, 100, 30);

        genre = new JTextField();
        genre.setBounds(150, 220, 200, 30);

        languageLabel = new JLabel("Język:");
        languageLabel.setBounds(20, 260, 100, 30);

        language = new JTextField();
        language.setBounds(150, 260, 200, 30);

        alleyLabel = new JLabel("Alejka:");
        alleyLabel.setBounds(20, 300, 55,30);

        alley = new JComboBox(new String[]{"-", "A", "B", "C", "D", "E"});
        alley.setBounds(75, 300, 55, 30);

        bookstandLabel = new JLabel("regał: ");
        bookstandLabel.setBounds(135,300,55,30);

        bookstand = new JComboBox(new String[]{"-", "a", "b", "c", "d", "e"});
        bookstand.setBounds(185,300,55,30);

        shelfLabel = new JLabel("półka:");
        shelfLabel.setBounds(245,300,55,30);

        shelf = new JComboBox(new String[]{"-", "1", "2", "3", "4", "5"});
        shelf.setBounds(295,300,55,30);

        oneAuthor = new JRadioButton("1 autor");
        oneAuthor.setBounds(400,20,200,50);
        oneAuthor.setSelected(true);

        moreAuthors = new JRadioButton("więcej autorów");
        moreAuthors.setBounds(400,50,200,50);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(oneAuthor);
        buttonGroup.add(moreAuthors);

        confirm = new JButton("Dodaj");
        confirm.setBounds(400, 100, 200, 50);

        back = new JButton("Cofnij");
        back.setBounds(400, 240, 200, 50);

        result = new JLabel();
        result.setBounds(20, 340, 460, 100);
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
        add(oneAuthor);
        add(moreAuthors);
        add(confirm);
        add(back);
        add(result);
    }

    private void action() {

        confirm.addActionListener(e -> {
            if(check()) {
                authorService.addAuthor(firstName.getText(), lastName.getText());
                bookService.addBook(title.getText(), genre.getText(), publisher.getText(), language.getText(),
                        firstName.getText(), lastName.getText(), alley.getSelectedItem().toString(),
                        bookstand.getSelectedItem().toString(), Integer.parseInt(shelf.getSelectedItem().toString()));
                result.setText(bookService.getMessage());
            } else {
                JOptionPane.showMessageDialog(null, "Uzupełnij dane.");
            }
        });
    }

    private boolean check(){

        boolean titleCheck = title.getText().length() > 0 && title.getText().length() < 50;
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

