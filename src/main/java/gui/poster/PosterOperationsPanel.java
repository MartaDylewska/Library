package gui.poster;

import images.IPosterDBService;
import images.PosterDBServiceImpl;

import javax.swing.*;
import java.io.File;

public class PosterOperationsPanel extends JPanel {
    private JButton addPosterBtn;
    private JButton browsePosterBtn;
    private JFileChooser fileChooser;
    private String filePath;

    private IPosterDBService posterDBService = new PosterDBServiceImpl();

    public PosterOperationsPanel(){
        setLayout(null);
        createAddPosterBtn();
        add(addPosterBtn);
        //createFileChooser();
        //add(fileChooser);
        createBrowsePosterBtn();
        add(browsePosterBtn);
        browsePosterBtn.addActionListener(e -> {
            fileChooser = new JFileChooser("C:\\Users\\e495405\\Desktop\\Baza danych zdjęcia\\biblio\\postery\\200_300");
            int r = fileChooser.showOpenDialog(this);
            if(r == JFileChooser.APPROVE_OPTION)
                setFilePath(fileChooser.getSelectedFile().getAbsolutePath());
            else
                setFilePath("użytkownik wycofał się z operacji");
            System.out.println(filePath);;
        });
        addPosterBtn.addActionListener(e -> {
            posterDBService.addImage(filePath);
            JOptionPane.showMessageDialog(this,"Plakat dodany do bazy plakatów");
        });


    }

    public void createBrowsePosterBtn(){
        browsePosterBtn = new JButton();
        browsePosterBtn.setText("Wyszukaj plakat...");
        browsePosterBtn.setBounds(50,50,200,50);
    }


    public void createAddPosterBtn(){
        addPosterBtn = new JButton();
        addPosterBtn.setText("Dodaj plakat");
        addPosterBtn.setBounds(300,50,200,50);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
