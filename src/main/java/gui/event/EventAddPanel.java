package gui.event;

import config.Validation;
import event.Event;
import event.EventDBServiceImpl;
import event.IEventDBService;
import gui.Auxiliary;
import gui.general.CustButton;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventAddPanel extends JPanel {
    private JLabel titleLbl, dateLbl, posterLbl, shortDescLbl;
    private JTextField titleTxt, dateTxt, posterTxt;
    private JTextArea shortDescTxt;
    private CustButton addEventBtn, browsePosterBtn,returnBtn;
    private int fieldLength = 200;
    private JFileChooser fileChooser;
    private JLabel rectLabel;
    private List<Component> componentBoldList = new ArrayList<>();
    private List<Component> componentPlainList = new ArrayList<>();

    private IPosterDBService posterDBService = new PosterDBServiceImpl();
    private IEventDBService eventDBService = new EventDBServiceImpl();

    public EventAddPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(true);
        createAllButtons();
        addAllButtons();
        addActionBrowsePosterBtn();
        actionAddEventBtn();
        createRectLabel();
        add(rectLabel);
        createCompList();
        setFont();
        Auxiliary.setImageAsBackground(this);
    }

    private void createCompList(){
        componentBoldList.add(titleLbl);
        componentBoldList.add(dateLbl);
        componentBoldList.add(posterLbl);
        componentBoldList.add(shortDescLbl);
        componentPlainList.add(titleTxt);
        componentPlainList.add(dateTxt);
        componentPlainList.add(shortDescTxt);
    }

    private void setFont(){
        for (Component c: componentBoldList)
            c.setFont(Auxiliary.panelFont);
        for (Component c: componentPlainList)
            c.setFont(Auxiliary.panelPlainFont);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(20,50,350,300);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void actionAddEventBtn() {
        addEventBtn.addActionListener(e -> {
            if (titleTxt.getText().equals("") || dateTxt.getText().equals("") || posterTxt.getText().equals("") || shortDescTxt.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Proszę wypełnić wszystkie pola");
            else if(Validation.checkIfDateOk(dateTxt.getText())== false)
                JOptionPane.showMessageDialog(this, "Niepoprawna data");
            else {
                posterDBService.addImage(getPosterTxt().getText());
                Poster posterForNewEvent = posterDBService.readLastImageFromDB();
                int newPosterId = posterForNewEvent.getIdImg();

                Event event = new Event();
                event.setImgId(newPosterId);
                event.setShortDescription(shortDescTxt.getText());
                event.setTitle(titleTxt.getText());
                event.setDateEvent(LocalDate.parse(dateTxt.getText()));
                eventDBService.addEvent(event);
                JOptionPane.showMessageDialog(this, "Nowe wydarzenie zostało dodane do bazy");
                setComponentsEditability(false);
            }
        });

    }

    private void addActionBrowsePosterBtn(){
        browsePosterBtn.addActionListener(e -> {
            fileChooser = new JFileChooser("C:\\Users\\e495405\\Desktop\\Baza danych zdjęcia\\biblio\\postery\\200_300");
            int r = fileChooser.showOpenDialog(this);
            if(r == JFileChooser.APPROVE_OPTION)
                getPosterTxt().setText(fileChooser.getSelectedFile().getAbsolutePath());
            else
                getPosterTxt().setText("");

        });
    }

    private void addAllButtons(){
        add(returnBtn);
        add(addEventBtn);
        add(browsePosterBtn);}

    private void createAllButtons(){
        createReturnBtn();
        createAddBtn();
        createBrowseBtn();
    }

    private void createBrowseBtn() {
        browsePosterBtn = new CustButton();
        browsePosterBtn.setText("Wyszukaj plakat");
        browsePosterBtn.setBounds(150, 140, 200, 30);
    }
    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 30);
    }

    private void createAddBtn() {
        addEventBtn = new CustButton();
        addEventBtn.setText("Wprowadź dane");
        addEventBtn.setBounds(400, 250, 200, 30);
    }

    private void addAllLabels() {
        add(titleLbl);
        add(titleTxt);
        add(dateLbl);
        add(dateTxt);
        add(posterLbl);
        add(posterTxt);
        add(shortDescLbl);
        add(shortDescTxt);
    }

    private void createAllLabels() {
        createTitleLbl();
        createTitleTxt();
        createDateLbl();
        createDateTxt();
        createPosterLbl();
        createPosterTxt();
        createShortDescLbl();
        createShortDescTxt();
    }

    private void createShortDescLbl() {
        shortDescLbl = new JLabel();
        shortDescLbl.setText("Krótki opis");
        shortDescLbl.setBounds(40, 180, 100, 30);
    }

    private void createShortDescTxt() {
        shortDescTxt = new JTextArea();
        shortDescTxt.setBounds(150, 180, fieldLength, 100);
        shortDescTxt.setWrapStyleWord(true);
        shortDescTxt.setLineWrap(true);
    }


    private void createPosterLbl() {
        posterLbl = new JLabel();
        posterLbl.setText("Plakat");
        posterLbl.setBounds(40, 140, 100, 30);
    }

    private void createPosterTxt() {
        posterTxt = new JTextField();
        posterTxt.setBounds(150, 380, fieldLength, 30);
        posterTxt.setVisible(false);
    }


    private void createDateLbl() {
        dateLbl = new JLabel();
        dateLbl.setText("Data");
        dateLbl.setBounds(40, 100, 100, 30);
    }

    private void createDateTxt() {
        dateTxt = new JTextField();
        dateTxt.setBounds(150, 100, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(40, 60, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 60, fieldLength, 30);
    }


    private void setCompVisibility(boolean visibility) {
        titleLbl.setVisible(visibility);
        dateLbl.setVisible(visibility);
        posterLbl.setVisible(visibility);
        shortDescLbl.setVisible(visibility);
        titleTxt.setVisible(visibility);
        dateTxt.setVisible(visibility);
        //posterTxt.setVisible(visibility);
        shortDescTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        titleTxt.setEditable(editability);
        dateTxt.setEditable(editability);
        posterTxt.setEditable(editability);
       shortDescTxt.setEditable(editability);
    }

    public JTextField getPosterTxt() {
        return posterTxt;
    }

    public void setPosterTxt(JTextField posterTxt) {
        this.posterTxt = posterTxt;
    }

    public CustButton getReturnBtn() {return returnBtn;}
}
