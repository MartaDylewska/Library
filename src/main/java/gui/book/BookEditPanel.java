package gui.book;

import book.*;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookEditPanel extends JPanel {

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField publisher, genre, language;
    private JTextArea title;
    private JLabel firstNameLabel, lastNameLabel;
    private JTextField firstName, lastName;
    private JLabel alleyLabel, bookstandLabel, shelfLabel;
    private JComboBox alley, bookstand, shelf;
    private CustButton confirm, returnBtn;
    private int fieldLength = 200;
    private List<Component> componentBoldList = new ArrayList<>();
    private List<Component> componentPlainList = new ArrayList<>();
    private JLabel rectLabel;

    private IBook bookService = new BookService();
    private IAuthor authorService = new AuthorService();
    private IAuthorBook authorBookService = new AuthorBookService();
    private IBookshelf bookshelfService = new BookshelfService();
    private int bookIdToEdit, authorIdToEdit;

    public BookEditPanel(BookGetPanel bookGetPanel) {

        this.bookIdToEdit = bookGetPanel.getBookIdToEdit();
        this.authorIdToEdit = bookGetPanel.getAuthorIdToEdit();
        Book book = bookService.getBook(bookIdToEdit);
        Author author = authorService.getAuthor(authorIdToEdit);

        setLayout(null);
        createComps(book, author);
        addComp();
        action(book, author);
        createCompBoldList();
        createCompPlainList();
        setFont();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        //rectLabel.setText("aa");
        rectLabel.setBounds(20,10,660,530);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void setFont(){
        for (Component c: componentBoldList)
            c.setFont(Auxiliary.panelFont);
        for (Component c: componentPlainList)
            c.setFont(Auxiliary.panelPlainFont);
    }

    private void createCompBoldList(){
        componentBoldList.add(titleLabel);
        componentBoldList.add(publisherLabel);
        componentBoldList.add(genreLabel);
        componentBoldList.add(languageLabel);
        componentBoldList.add(firstNameLabel);
        componentBoldList.add(lastNameLabel);
        componentBoldList.add(alleyLabel);
        componentBoldList.add(bookstandLabel);
        componentBoldList.add(shelfLabel);
    }

    private void createCompPlainList(){
        componentPlainList.add(publisher);
        componentPlainList.add(genre);
        componentPlainList.add(language);
        componentPlainList.add(title);
        componentPlainList.add(firstName);
        componentPlainList.add(lastName);
        componentPlainList.add(alley);
        componentPlainList.add(bookstand);
        componentPlainList.add(shelf);
    }

    private void createComps(Book book, Author author) {
        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(50, 60, 100, 30);

        title = new JTextArea();
        title.setBounds(180, 60, fieldLength, 70);
        title.setBorder(BorderFactory.createLineBorder(Color.black));
        title.setLineWrap(true);
        title.setText(book.getTitle());

        firstNameLabel = new JLabel("Imię autora:");
        firstNameLabel.setBounds(50, 140, 100, 30);

        firstName = new JTextField();
        firstName.setBounds(180, 140, fieldLength, 30);
        firstName.setText(author.getFirstName());

        lastNameLabel = new JLabel("Nazwisko autora: ");
        lastNameLabel.setBounds(50, 180, 120, 30);

        lastName = new JTextField();
        lastName.setBounds(180, 180, fieldLength, 30);
        lastName.setText(author.getLastName());

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(50, 220, 100, 30);

        publisher = new JTextField();
        publisher.setBounds(180, 220, fieldLength, 30);
        publisher.setText(book.getPublisher());

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(50, 260, 100, 30);

        genre = new JTextField();
        genre.setBounds(180, 260, fieldLength, 30);
        genre.setText(book.getGenre());

        languageLabel = new JLabel("Język:");
        languageLabel.setBounds(50, 300, 100, 30);

        language = new JTextField();
        language.setBounds(180, 300, fieldLength, 30);
        language.setText(book.getLanguage());

        alleyLabel = new JLabel("Alejka:");
        alleyLabel.setBounds(50, 340, 55, 30);

        String[] alleys = bookshelfService.getAlleys();
        alley = new JComboBox(alleys);
        alley.setBounds(105, 340, 55, 30);
        String alleyBook = book.getBookshelf().getAlley();
        alley.setSelectedItem(alleyBook);

        bookstandLabel = new JLabel("regał: ");
        bookstandLabel.setBounds(165,340,55,30);

        String[] bookstands = bookshelfService.getBookstands();
        bookstand = new JComboBox(bookstands);
        bookstand.setBounds(215,340,55,30);
        String bookstandBook = book.getBookshelf().getBookstand();
        bookstand.setSelectedItem(bookstandBook);

        shelfLabel = new JLabel("półka:");
        shelfLabel.setBounds(275,340,55,30);

        String[] shelves = bookshelfService.getShelves();
        shelf = new JComboBox(shelves);
        shelf.setBounds(325,340,55,30);
        String shelfBook = String.valueOf(book.getBookshelf().getShelf());
        shelf.setSelectedItem(shelfBook);

        confirm = new CustButton();
        confirm.setText("Zatwierdź");
        confirm.setBounds(400, 60, 200, 30);

        returnBtn = new CustButton();
        returnBtn.setText("Anuluj");
        returnBtn.setBounds(400, 340, 200, 30);

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
        add(returnBtn);
    }

    private void action(Book book, Author author) {

        confirm.addActionListener(e -> {
            if(check()) {
                if(book == null || author == null)
                    JOptionPane.showMessageDialog(this,"Proszę zaznaczyć książkę");
                if(!(firstName.getText().equals(author.getFirstName())) || !(lastName.getText().equals(author.getLastName()))) {
                    authorService.addAuthor(firstName.getText(), lastName.getText());
                    authorBookService.editAuthorBook(bookIdToEdit, firstName.getText(), lastName.getText());
                }
                if(!(alley.getSelectedItem().equals(book.getBookshelf().getAlley())) || !(bookstand.getSelectedItem().equals(book.getBookshelf().getBookstand())) || !(shelf.getSelectedItem().equals(String.valueOf(book.getBookshelf().getShelf()))))
                    bookService.editBook(bookIdToEdit, alley.getSelectedItem().toString(), bookstand.getSelectedItem().toString(), Integer.parseInt(shelf.getSelectedItem().toString()));
                if(!(title.getText().equals(book.getTitle())) || !(publisher.getText().equals(book.getPublisher())) || !(genre.getText().equals(book.getGenre())) || !(language.getText().equals(book.getLanguage())))
                    bookService.editBook(bookIdToEdit, title.getText(), publisher.getText(), genre.getText(), language.getText());

                JOptionPane.showMessageDialog(this, "Książka została zaktualizowana");
            } else {
                JOptionPane.showMessageDialog(this, "Uzupełnij dane.");
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

        return titleCheck && firstNameCheck && lastNameCheck && publisherCheck &&
                genreCheck && languageCheck;
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}