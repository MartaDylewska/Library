package gui.event;

import event.Event;
import event.EventDBServiceImpl;
import event.IEventDBService;
import gui.general.MyButton;
import gui.reader.ReaderEntryPanel;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.util.List;

public class EventSeeAllPanel extends JPanel {
    private JComboBox eventChooserBx;
    private MyButton viewEventBtn,  returnBtn;
    private JLabel titleLbl, dateLbl, posterLbl, shortDescLbl, posterShowLbl;
    private JTextField titleTxt, dateTxt;
    private JTextArea shortDescTxt;
    private JLabel cardIdTxt;
    int fieldLength = 200;
    int deltaY = 20;
    Event selectedEvent = null;
    ReaderEntryPanel readerEntryPanel;

    private IPosterDBService posterDBService = new PosterDBServiceImpl();
    private IEventDBService eventDBService = new EventDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();

    public EventSeeAllPanel(ReaderEntryPanel readerEntryPanel) {

        setLayout(null);
        this.readerEntryPanel = readerEntryPanel;
        createCardIdTxt();
        add(cardIdTxt);
        cardIdTxt.setText(readerEntryPanel.getCardNrLbl().getText());
        createItems();
        addItems();
        cardIdTxt.setVisible(false);
        setCompVisibility(false);
        setComponentsEditability(false);
        addActionViewEventBtn();
    }

    private void addItems() {
        //add(cardIdTxt);
        System.out.println(cardIdTxt.getText());
        add(eventChooserBx);
        add(dateLbl);
        add(dateTxt);
        add(posterLbl);
        add(posterShowLbl);
        add(shortDescLbl);
        add(shortDescTxt);
        add(titleLbl);
        add(titleTxt);
        add(viewEventBtn);
        add(returnBtn);

    }

    private void createItems() {
        //createCardIdTxt();
        createEventChooserBx();
        createDateLbl();
        createDateTxt();
        createPosterLbl();
        createPosterShowLbl();
        createShortDescLbl();
        createShortDescTxt();
        createTitleLbl();
        createTitleTxt();
        createViewEventBtn();
        createReturnBtn();

    }


    private void addActionViewEventBtn() {
        viewEventBtn.addActionListener(e -> {
            List<Event> eventList = eventDBService.getAllEventsFromDB();

            if (eventChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                for (Event event : eventList) {
                    if ((event.getDateEvent().toString() + " " + event.getTitle()).equals(eventChooserBx.getSelectedItem())) {
                        selectedEvent = event;
                    }
                }
                titleTxt.setText(selectedEvent.getTitle());
                dateTxt.setText(selectedEvent.getDateEvent().toString());
                shortDescTxt.setText(selectedEvent.getShortDescription());
                posterDBService = new PosterDBServiceImpl();
                Poster poster = posterDBService.readImageById(selectedEvent.getImgId());
                ImageIcon icon = new ImageIcon(poster.getImgBytes());
                posterShowLbl.setIcon(icon);
            } else {
                JOptionPane.showMessageDialog(this, "Proszę wybrać wydarzenie");
            }
        });
    }

    private void createCardIdTxt(){
        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
    }


    private void createViewEventBtn() {
        viewEventBtn = new MyButton(true);
        viewEventBtn.setText("Podgląd");
        viewEventBtn.setBounds(450, 10, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new MyButton(false);
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 50);
    }

    private void createEventChooserBx() {

        eventChooserBx = new JComboBox();

        //int idEvent = selectedEvent.getIdEvent();
        User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        List<Event> eventList = eventDBService.getAllEventsForUser(reader.getIdReader());
        eventChooserBx.addItem("---wybierz---");
        for (Event e : eventList) {
            eventChooserBx.addItem(e.getDateEvent().toString() + " " + e.getTitle());
        }
        eventChooserBx.setBounds(30, 10, 300, 50);
    }

    private void createShortDescLbl() {
        shortDescLbl = new JLabel();
        shortDescLbl.setText("Krótki opis");
        shortDescLbl.setBounds(20, 140 + deltaY + 300 + 20, 100, 30);
    }

    private void createShortDescTxt() {
        shortDescTxt = new JTextArea();
        shortDescTxt.setBounds(150, 140 + deltaY + 300 + 20, fieldLength, 100);
        shortDescTxt.setWrapStyleWord(true);
        shortDescTxt.setLineWrap(true);
    }


    private void createPosterLbl() {
        posterLbl = new JLabel();
        posterLbl.setText("Plakat");
        posterLbl.setBounds(20, 140 + deltaY, 100, 30);
    }

    private void createPosterShowLbl() {
        posterShowLbl = new JLabel();
        posterShowLbl.setBounds(150, 140 + deltaY, fieldLength, 300);
    }


    private void createDateLbl() {
        dateLbl = new JLabel();
        dateLbl.setText("Data");
        dateLbl.setBounds(20, 100 + deltaY, 100, 30);
    }

    private void createDateTxt() {
        dateTxt = new JTextField();
        dateTxt.setBounds(150, 100 + deltaY, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(20, 60 + deltaY, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 60 + deltaY, fieldLength, 30);
    }


    private void setCompVisibility(boolean visibility) {
        titleLbl.setVisible(visibility);
        dateLbl.setVisible(visibility);
        posterLbl.setVisible(visibility);
        shortDescLbl.setVisible(visibility);
        titleTxt.setVisible(visibility);
        dateTxt.setVisible(visibility);
        posterShowLbl.setVisible(visibility);
        shortDescTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        titleTxt.setEditable(editability);
        dateTxt.setEditable(editability);
        shortDescTxt.setEditable(editability);
    }

    public JLabel getPosterShowLbl() {
        return posterShowLbl;
    }

    public void setPosterShowLbl(JLabel posterShowLbl) {
        this.posterShowLbl = posterShowLbl;
    }

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }

    public MyButton getReturnBtn() {
        return returnBtn;
    }
}
