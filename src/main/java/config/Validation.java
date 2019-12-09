package config;

import admin.Admin;
import admin.AdminDBServiceImpl;
import admin.IAdminDBService;
import gui.login.LoginPanel;
import librarian.ILibrarianDBService;
import librarian.Librarian;
import librarian.LibrarianDBServiceImpl;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public static boolean checkUserExists(int cardId){
        IUserDBService userDBService = new UserDBServiceImpl();
        User user = userDBService.readUserFromDB(cardId);
        if(user.getIdUser()!=0)
            return true;
        return false;
    }

    public static boolean checkPassOk(User user, String password){
        if(user.getPassword().equals(password))
            return true;
        return false;
    }

    public static boolean checkIfReader(User user){
        IReaderDBService readerDBService = new ReaderDBServiceImpl();
        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        if(reader.getIdReader()!=0)
            return true;
        return false;
    }

    public static boolean checkIfLibrarian(User user){
        ILibrarianDBService librarianDBService = new LibrarianDBServiceImpl();
        Librarian librarian = librarianDBService.readLibrarianFromDB(user.getCardNumber());
        if(librarian.getIdLibrarian()!=0)
            return true;
        return false;
    }

    public static boolean checkIfAdmin(User user){
        IAdminDBService adminDBService = new AdminDBServiceImpl();
        Admin admin = adminDBService.readAdminFromDB(user.getCardNumber());
        if(admin.getIdAdmin()!=0)
            return true;
        return false;
    }

    public static boolean checkIfInteger(String cardIdTxt) {
        String regex = "[+-]?[0-9][0-9]*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cardIdTxt);
        if (m.find() && m.group().equals(cardIdTxt))
            return true;
        return false;
    }

    public static boolean checkIfEmailOK(String email) {
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }

    public static boolean checkIfPostalCodeOK(String cityName){
        if(cityName.equals(""))
            return false;
        return true;
    }

    private static final ThreadLocal<SimpleDateFormat> format = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            System.out.println("created");
            return df;
        }
    };

    public static boolean checkIfDateOk(String text) {
        if (text == null || !text.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;
        try {
            format.get().parse(text);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }


}
