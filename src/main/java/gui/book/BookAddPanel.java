package gui.book;

import book.*;
import gui.Auxiliary;
import gui.general.CustButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookAddPanel extends JPanel {

    private JLabel titleLabel, publisherLabel, genreLabel, languageLabel;
    private JTextField publisher, genre, language;
    private JTextArea title;
    private JLabel firstNameLabel, lastNameLabel, moreAuthorsLabel;
    private JTextField firstName, lastName;
    private JTextField[] names;
    private JLabel alleyLabel, bookstandLabel, shelfLabel;
    private JComboBox alley, bookstand, shelf;
    private CustButton confirm, returnBtn;
    private JRadioButton oneAuthor, moreAuthors;
    private ButtonGroup buttonGroup;
    private String alertMessage;
    private List<Component> componentBoldList = new ArrayList<>();
    private List<Component> componentPlainList = new ArrayList<>();
    private JLabel rectLabel;

    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();
    private IAuthorBook authorBookService = new AuthorBookService();
    private IBookshelf bookshelfService = new BookshelfService();


    public BookAddPanel() {

        setLayout(null);
        createComps();
        addComp();
        action();
        setSeen(false);
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
        componentBoldList.add(moreAuthorsLabel);
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
        componentPlainList.add(oneAuthor);
        componentPlainList.add(moreAuthorsLabel);
        componentPlainList.add(moreAuthors);
    }

    private void createComps() {

        titleLabel = new JLabel("Tytuł:");
        titleLabel.setBounds(50, 20, 100, 30);

        title = new JTextArea();
        title.setBounds(180, 20, 200, 70);
        title.setBorder(Auxiliary.blackBorder());
        title.setLineWrap(true);

        firstNameLabel = new JLabel("Imię autora:");
        firstNameLabel.setBounds(50, 100, 100, 30);

        firstName = new JTextField();
        firstName.setBounds(180, 100, 200, 30);
        firstName.setBorder(Auxiliary.blackBorder());

        lastNameLabel = new JLabel("Nazwisko autora: ");
        lastNameLabel.setBounds(50, 140, 120, 30);

        lastName = new JTextField();
        lastName.setBounds(180, 140, 200, 30);
        lastName.setBorder(Auxiliary.blackBorder());

        publisherLabel = new JLabel("Wydawca:");
        publisherLabel.setBounds(50, 180, 100, 30);

        publisher = new JTextField();
        publisher.setBounds(180, 180, 200, 30);
        publisher.setBorder(Auxiliary.blackBorder());

        genreLabel = new JLabel("Gatunek:");
        genreLabel.setBounds(50, 220, 100, 30);

        genre = new JTextField();
        genre.setBounds(180, 220, 200, 30);
        genre.setBorder(Auxiliary.blackBorder());

        languageLabel = new JLabel("Język:");
        languageLabel.setBounds(50, 260, 100, 30);

        language = new JTextField();
        language.setBounds(180, 260, 200, 30);
        language.setBorder(Auxiliary.blackBorder());

        alleyLabel = new JLabel("Alejka:");
        alleyLabel.setBounds(50, 300, 55,30);

        String[] alleys = bookshelfService.getAlleys();
        alley = new JComboBox(alleys);
        alley.setBounds(105, 300, 55, 30);

        bookstandLabel = new JLabel("regał: ");
        bookstandLabel.setBounds(165,300,55,30);

        String[] bookstands = bookshelfService.getBookstands();
        bookstand = new JComboBox(bookstands);
        bookstand.setBounds(215,300,55,30);

        shelfLabel = new JLabel("półka:");
        shelfLabel.setBounds(275,300,55,30);

        String[] shelves = bookshelfService.getShelves();
        shelf = new JComboBox(shelves);
        shelf.setBounds(325,300,55,30);

        moreAuthorsLabel = new JLabel("Autorzy:");
        moreAuthorsLabel.setBounds(50,340,200,30);
        moreAuthorsLabel.setVisible(false);

        names = new JTextField[10];
        for (int i = 0; i < names.length; i++) {
            names[i] = new JTextField();
           // names[i].setBorder(Auxiliary.blackBorder());
            if(i%2 == 0)
                names[i].setBounds(50,380 + (30 * (i / 2)), 200,30);
            else
                names[i].setBounds(250,380 + (30 * (i / 2)), 200,30);
        }

        oneAuthor = new JRadioButton("1 autor");
        oneAuthor.setBounds(400,20,200,30);
        oneAuthor.setSelected(true);

        moreAuthors = new JRadioButton("więcej autorów");
        moreAuthors.setBounds(400,50,200,30);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(oneAuthor);
        buttonGroup.add(moreAuthors);

        confirm = new CustButton();
        confirm.setText("Dodaj");
        confirm.setBounds(400, 100, 200, 30);

        returnBtn = new CustButton();
        returnBtn.setText("Cofnij");
        returnBtn.setBounds(400, 240, 200, 30);
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
        add(moreAuthorsLabel);
        add(confirm);
        add(returnBtn);
        for (JTextField name : names) {
            add(name);
        }
    }

    private void action() {

        confirm.addActionListener(e1 -> {
            if(oneAuthor.isSelected()) {
                if (check()) {

                    authorService.addAuthor(firstName.getText(), lastName.getText());
                    bookService.addBook(title.getText(), genre.getText(), publisher.getText(), language.getText(),
                            alley.getSelectedItem().toString(), bookstand.getSelectedItem().toString(), Integer.parseInt(shelf.getSelectedItem().toString()));
                    authorBookService.addAuthorBook(firstName.getText(), lastName.getText(), title.getText());
                    JOptionPane.showMessageDialog(this, "Książka została dodana do bazy");

                } else {
                    JOptionPane.showMessageDialog(this, alertMessage);
                }
            } else {
                firstName.setText("none");
                lastName.setText("none");
                if(check()) {
                    bookService.addBook(title.getText(), genre.getText(), publisher.getText(), language.getText(),
                            alley.getSelectedItem().toString(), bookstand.getSelectedItem().toString(), Integer.parseInt(shelf.getSelectedItem().toString()));
                    for (int i = 0; i < names.length; i+= 2){
                        if(names[i].getText().length() > 0) {
                            authorService.addAuthor(names[i].getText(), names[i + 1].getText());
                            authorBookService.addAuthorBook(names[i].getText(), names[i + 1].getText(), title.getText());
                        }
                    }
                    JOptionPane.showMessageDialog(this, "Książka została dodana do bazy");

                } else {
                    JOptionPane.showMessageDialog(this, alertMessage);
                }
            }
        });

        oneAuthor.addActionListener(e ->{
            firstName.setEditable(true);
            lastName.setEditable(true);
            setSeen(false);
        });

        moreAuthors.addActionListener(e ->{
            firstName.setEditable(false);
            lastName.setEditable(false);
            setSeen(true);
        });
    }

    private boolean check(){

        alertMessage = "Uzupełnij dane: ";

        boolean titleCheck = title.getText().length() > 0 && title.getText().length() < 50;
        if(!titleCheck)
            alertMessage = alertMessage + "\n- brak tytułu";
        boolean firstNameCheck = firstName.getText().length() > 0 && firstName.getText().length() < 20;
        if(!firstNameCheck)
            alertMessage = alertMessage + "\n- brak imienia";
        boolean lastNameCheck = lastName.getText().length() > 0 && lastName.getText().length() < 20;
        if(!lastNameCheck)
            alertMessage = alertMessage + "\n- brak nazwiska";
        boolean publisherCheck = publisher.getText().length() > 0 && publisher.getText().length() < 20;
        if(!publisherCheck)
            alertMessage = alertMessage +  "\n- brak wydawcy";
        boolean genreCheck = genre.getText().length() > 0 && genre.getText().length() < 20;
        if(!genreCheck)
            alertMessage = alertMessage + "\n- brak gatunku";
        boolean languageCheck = language.getText().length() > 0 && language.getText().length() < 20;
        if (!languageCheck)
            alertMessage = alertMessage + "\n- brak języka";
        alertMessage = alertMessage + ".";

        return titleCheck && firstNameCheck && lastNameCheck && publisherCheck &&
                genreCheck && languageCheck;
    }

    private void setSeen(boolean seen){
        moreAuthorsLabel.setVisible(seen);
        for (JTextField name : names) {
            name.setVisible(seen);
        }
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}
