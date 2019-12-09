package gui.admin;

import admin.Admin;
import admin.AdminDBServiceImpl;
import admin.IAdminDBService;
import librarian.ILibrarianDBService;
import librarian.LibrarianDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AdminsShowAllPanel extends JPanel {

    private JList<Admin> adminJList;
    JScrollPane listScroller;
    private JButton returnBtn;

    private IAdminDBService adminDBService = new AdminDBServiceImpl();

    public AdminsShowAllPanel(){
        setLayout(null);
        createAdminJList(getAdminsList());
        add(adminJList);
        add(listScroller);
        createReturnBtn();
        add(returnBtn);
    }

    private void createAdminJList(List<Admin> arrayList){
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < arrayList.size(); i++)
        {
            listModel.addElement(arrayList.get(i));
        }
        adminJList = new JList<>();
        adminJList.setModel(listModel);
        adminJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adminJList.setLayoutOrientation(JList.VERTICAL);
        adminJList.setVisibleRowCount(-1);
        adminJList.setBounds(20,20,300,400);
        listScroller = new JScrollPane(adminJList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    public List<Admin> getAdminsList() { return adminDBService.getAllAdminsFromDB();}

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400,300,200,50);
    }

    public JButton getReturnBtn(){
        return returnBtn;
    }
}
