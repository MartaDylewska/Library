package gui.book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import book.*;
import gui.general.MyButton;

public class AuthorGetPanel extends JPanel {

    private JLabel keyWordLabel, resultListLabel, result, newFirstNameLabel, newLastNameLabel;
    private JTextField keyWord, newFirstName, newLastName;
    private JList resultList;
    private MyButton search, remove, edit, change, back;
    private JScrollPane listScroller;

    private IAuthor authorService = new AuthorService();
    private IAuthorBook authorBookService = new AuthorBookService();

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
        listScroller = new JScrollPane(resultList);
        listScroller.setPreferredSize(new Dimension(250, 80));

    }

    private void createComps() {

        keyWordLabel = new JLabel("Słowo kluczowe:");
        keyWordLabel.setBounds(80,40,100,30);

        keyWord = new JTextField();
        keyWord.setBounds(180,40,200,30);

        resultListLabel = new JLabel("Wyniki wyszukiwania:");
        resultListLabel.setBounds(80,80,200,30);

        resultList = new JList();
        resultList.setBounds(80,120,300,320);
        resultList.setBorder(BorderFactory.createLineBorder(Color.black));

        result = new JLabel();
        result.setBounds(80,120,300,320);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setBackground(Color.white);
        result.setOpaque(true);
        result.setVerticalAlignment(1);

        search = new MyButton(true);
        search.setText("Szukaj");
        search.setBounds(400,40,200,30);

        remove = new MyButton(true);
        remove.setText("Usuń");
        remove.setBounds(400,80,200,30);

        edit = new MyButton(true);
        edit.setText("Edytuj");
        edit.setBounds(400,120,200,30);

        newFirstNameLabel = new JLabel("Nowe imię:");
        newFirstNameLabel.setBounds(400,250,200,30);

        newFirstName = new JTextField();
        newFirstName.setBounds(400,290,200,30);
        newFirstName.setEditable(false);

        newLastNameLabel = new JLabel("Nowe nazwisko:");
        newLastNameLabel.setBounds(400,320,200,30);

        newLastName = new JTextField();
        newLastName.setBounds(400,370,200,30);
        newLastName.setEditable(false);

        change = new MyButton(false);
        change.setText("Zatwierdź");
        change.setBounds(400,410,200,30);
        change.setEnabled(false);

        back = new MyButton(false);
        back.setText("Cofnij");
        back.setBounds(400,470,200,30);

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
                JOptionPane.showMessageDialog(this, "Żaden autor nie został wybrany.");
            } else {

                if (JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć autora?", "UWAGA!",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    authorBookService.removeBooksOfAuthor(author.getFirstName(), author.getLastName());
                    authorService.removeAuthor(author.getAuthorId());
                    repaint();
                    revalidate();
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
                    authorService.editAuthor(author.getAuthorId(), newFirstName.getText(), newLastName.getText());
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

    public MyButton getBack() {
        return back;
    }

}