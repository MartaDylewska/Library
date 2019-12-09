package gui.librarian;

import librarian.ILibrarianDBService;
import librarian.Librarian;
import librarian.LibrarianDBServiceImpl;
import reader.IReaderDBService;
import reader.ReaderDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LibrariansShowAllPanel extends JPanel {

    private JList<Librarian> librarianJList;
    JScrollPane listScroller;
    private JButton returnBtn;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private ILibrarianDBService librarianDBService = new LibrarianDBServiceImpl();

    public LibrariansShowAllPanel(){
        setLayout(null);
        createLibrarianJList(getLibrariansList());
        add(librarianJList);
        add(listScroller);
        createReturnBtn();
        add(returnBtn);
    }

    private void createLibrarianJList(List<Librarian> arrayList){
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < arrayList.size(); i++)
        {
            listModel.addElement(arrayList.get(i));
        }
        librarianJList = new JList<>();
        librarianJList.setModel(listModel);
        librarianJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        librarianJList.setLayoutOrientation(JList.VERTICAL);
        librarianJList.setVisibleRowCount(-1);
        librarianJList.setBounds(20,20,300,400);
        listScroller = new JScrollPane(librarianJList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    //public List<User> getUserList(){
    //    return userDBService.getAllUsersFromDB();
    //}

    public List<Librarian> getLibrariansList() { return librarianDBService.getAllLibrariansFromDB();}

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400,300,200,50);
    }

    public JButton getReturnBtn(){
        return returnBtn;
    }
}
