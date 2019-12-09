package gui.reader;

import librarian.Librarian;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ReadersShowAllPanel  extends JPanel {

    private JList<Reader> readerJList;
    JScrollPane listScroller;
    private JButton returnBtn;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public ReadersShowAllPanel(){
        setLayout(null);
        createReaderJList(getReadersList());
        add(readerJList);
        add(listScroller);
        createReturnBtn();
        add(returnBtn);
    }

    private void createReaderJList(List<Reader> arrayList){
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < arrayList.size(); i++)
        {
            listModel.addElement(arrayList.get(i));
        }
        readerJList = new JList<Reader>();
        readerJList.setModel(listModel);
        readerJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        readerJList.setLayoutOrientation(JList.VERTICAL);
        readerJList.setVisibleRowCount(-1);
        readerJList.setBounds(20,20,300,400);
        listScroller = new JScrollPane(readerJList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    //public List<User> getUserList(){
    //    return userDBService.getAllUsersFromDB();
    //}

    public List<Reader> getReadersList() { return readerDBService.getAllReadersFromDB();}

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400,300,200,50);
    }

    public JButton getReturnBtn(){
        return returnBtn;
    }
}
