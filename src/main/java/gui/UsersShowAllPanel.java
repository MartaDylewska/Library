package gui;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class UsersShowAllPanel extends JPanel {

    private JList<User> userJList;
    JScrollPane listScroller;
    private JButton returnBtn;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();

    public UsersShowAllPanel(){
        setLayout(null);
        createUserJList(getUserList());
        add(userJList);
        add(listScroller);
        createReturnBtn();
        add(returnBtn);
    }

    private void createUserJList(List<User> arrayList){
        DefaultListModel listModel = new DefaultListModel();
        for (int i = 0; i < arrayList.size(); i++)
        {
            listModel.addElement(arrayList.get(i));
        }
        userJList = new JList<>();
        userJList.setModel(listModel);
        userJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userJList.setLayoutOrientation(JList.VERTICAL);
        userJList.setVisibleRowCount(-1);
        userJList.setBounds(20,20,300,400);
        listScroller = new JScrollPane(userJList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    public List<User> getUserList(){
        return userDBService.getAllUsersFromDB();
    }

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400,300,200,50);
    }

    public JButton getReturnBtn(){
        return returnBtn;
    }

}
