package framework.membership.Proxy.Validate;

import framework.membership.Config;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Duong Truong on 2/2/2018.
 */
public class ValidateImplement implements IValidate {

    private static Pattern regexPattern;
    private static Matcher regMatcher;

    @Override
    public boolean validateString(StringType type, String input) {
        switch (type){
            case Email: return validateEmailAddress(input);
            case Phone: return validateMobileNumber(input);
            case Digit: return validateDigit(input);
            case Boolean: return validateBoolean(input);
            case Password: return validatePassword(input);
        }
        return false;
    }

    public static boolean validateEmailAddress(String emailAddress) {
        regexPattern = Pattern.compile("^[(a-zA-Z-0-9-\\_\\+\\.)]+@[(a-z-A-z)]+\\.[(a-zA-z)]{2,3}$");
        regMatcher = regexPattern.matcher(emailAddress);
        return regMatcher.matches();
    }

    public static boolean validateMobileNumber(String mobileNumber) {
        regexPattern = Pattern.compile("\\d{10}");
        regMatcher   = regexPattern.matcher(mobileNumber);
        return regMatcher.matches();
    }

    public static boolean validateDigit(String digit){
        regexPattern = Pattern.compile("[0-9]+");
        regMatcher = regexPattern.matcher(digit);
        return regMatcher.matches();
    }

    public static boolean validateBoolean(String booleanString){
        List<String> booleanStringList = Arrays.asList("0","1"
        ,"true","false","yes","no","check","uncheck");

        return booleanStringList.contains(booleanString.toLowerCase());
    }
    public static boolean validatePassword(String password){
        int passworkStrength = Config.PASSWORD_STRENGTH;
        return calculatePasswordStrength(password) >= passworkStrength;
    }
    private static int calculatePasswordStrength(String password){

        //total score of password
        int iPasswordScore = 0;

        if( password.length() < 8 )
            return 0;
        else if( password.length() >= 10 )
            iPasswordScore += 2;
        else
            iPasswordScore += 1;

        //if it contains one digit, add 2 to total score
        if( password.matches("(?=.*[0-9]).*") )
            iPasswordScore += 2;

        //if it contains one lower case letter, add 2 to total score
        if( password.matches("(?=.*[a-z]).*") )
            iPasswordScore += 2;

        //if it contains one upper case letter, add 2 to total score
        if( password.matches("(?=.*[A-Z]).*") )
            iPasswordScore += 2;

        //if it contains one special character, add 2 to total score
        if( password.matches("(?=.*[~!@#$%^&*()_-]).*") )
            iPasswordScore += 2;

        return iPasswordScore;

    }
}
