package util;
import java.util.regex.Pattern;

public class Validator {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final String PHONE_REGEX = "^[0-9]{10,15}$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(EMAIL_REGEX, email);
    }

    public static boolean isValidPhone(String phone) {
    	return true;
        //return Pattern.matches(PHONE_REGEX, phone);
    }
}
