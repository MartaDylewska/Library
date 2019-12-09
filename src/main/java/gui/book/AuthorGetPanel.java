package gui.book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import book.*;

public class AuthorGetPanel extends JPanel {

    private JLabel keyWordLabel, resultListLabel, result, newFirstNameLabel, newLastNameLabel;
    private JTextField keyWord, newFirstName, newLastName;
    private JList resultList;
    private JButton search, remove, edit, change, back;

    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();

    public AuthorGetPanel() {

        setLayout(null);

        createComps();
        addComps();
        actions();
    }

    private void createBookJList(List<Author> authorList){

        DefaultListModel listModel = new DefaultListModel();
        for (Author author : authorList) {
            listModel.addElement(author);
        }
        resultList.setModel(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(resultList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void createComps() {

        keyWordLabel = new JLabel("Słowo kluczowe:");
        keyWordLabel.setBounds(20,20,100,30);

        keyWord = new JTextField();
        keyWord.setBounds(150,20,200,30);

        resultListLabel = new JLabel("Wyniki wyszukiwania:");
        resultListLabel.setBounds(20,60,200,30);

        resultList = new JList();
        resultList.setBounds(20,100,330,320);
        resultList.setBorder(BorderFactory.createLineBorder(Color.black));

        result = new JLabel();
        result.setBounds(20,100,330,320);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setBackground(Color.white);
        result.setOpaque(true);

        search = new JButton("szukaj");
        search.setBounds(400,20,200,50);

        remove = new JButton("usuń");
        remove.setBounds(400,70,200,50);

        edit = new JButton("edytuj");
        edit.setBounds(400,120,200,50);

        newFirstNameLabel = new JLabel("Nowe imię:");
        newFirstNameLabel.setBounds(400,230,200,30);

        newFirstName = new JTextField();
        newFirstName.setBounds(400,270,200,30);
        newFirstName.setEditable(false);

        newLastNameLabel = new JLabel("Nowe nazwisko:");
        newLastNameLabel.setBounds(400,310,200,30);

        newLastName = new JTextField();
        newLastName.setBounds(400,350,200,30);
        newLastName.setEditable(false);

        change = new JButton("zatwierdź");
        change.setBounds(400,390,200,30);
        change.setEnabled(false);

        back = new JButton("cofnij");
        back.setBounds(400,470,200,50);

    }

    private void actions(){

        search.addActionListener(e -> {
            List<Author> authorList;

            if(keyWord.getText().length() == 0)
                authorList = authorService.getAuthors();
            else {
                authorList = authorService.getAuthors(keyWord.getText());
            }

            if(authorList.size() > 0) {
                createBookJList(authorList);
                add(resultList);
            } else {
                result.setText("Nie ma takich autorów.");
            }
        });

        remove.addActionListener(e ->{
            Author author = (Author) resultList.getSelectedValue();

            if(author == null) {
                JOptionPane.showMessageDialog(null, "Żaden autor nie został wybrany.");
            } else {

                if (JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć autora?", "UWAGA!",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    bookService.removeBook(author.getFirstName(), author.getLastName());
                    authorService.removeAuthor(author.getFirstName(), author.getLastName());
                    List<Author> authorList = authorService.getAuthors();
                    createBookJList(authorList);
                    add(resultList);
                    result.setText(authorService.getMessage());
                }
            }
        });

        edit.addActionListener(e ->{

            Author author = (Author) resultList.getSelectedValue();
            if(author == null){
                JOptionPane.showMessageDialog(null, "Żaden autor nie został wybrany.");
            } else {
                newFirstName.setEditable(true);
                newFirstName.setText(author.getFirstName());
                newLastName.setEditable(true);
                newLastName.setText(author.getLastName());
                change.setEnabled(true);

                change.addActionListener(e1 ->{
                    bookService.editBook(author.getId(), newFirstName.getText(), newLastName.getText());
                    authorService.editAuthor(author.getId(), newFirstName.getText(), newLastName.getText());
                    result.setText(authorService.getMessage());
                });
            }

        });
    }

    private void addComps(){

        add(keyWordLabel);
        add(keyWord);
        add(resultListLabel);
        add(result);
        add(search);
        add(remove);
        add(edit);
        add(newFirstNameLabel);
        add(newFirstName);
        add(newLastNameLabel);
        add(newLastName);
        add(change);
        add(back);
    }

    public JButton getBack() {
        return back;
    }

}
