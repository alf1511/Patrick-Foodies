package core;

import java.util.regex.Pattern;

public class Validator {
    
    public static boolean phoneNumber(String phoneNumber){
        String regexPattern = "(\\+62 ((\\d{3}([ -]\\d{3,})([- ]\\d{4,})?)|(\\d+)))|(\\(\\d+\\) \\d+)|\\d{3}( \\d+)+|(\\d+[ -]\\d+)|\\d+";
        return Pattern.compile(regexPattern).matcher(phoneNumber).matches();
    }
    
    public static boolean password(String pass){
        return pass.length()>=5;
    }
    
    public static boolean username(String uname){
        return uname.length()>=5;
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
