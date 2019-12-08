package config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

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
