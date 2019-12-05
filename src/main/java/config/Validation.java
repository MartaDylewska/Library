package config;

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
}
