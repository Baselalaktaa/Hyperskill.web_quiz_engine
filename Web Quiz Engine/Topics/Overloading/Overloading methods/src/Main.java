import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static long getMaxMinusCurrent(long val) {
        return Long.MAX_VALUE - val;
    }

    // write a method here
    // public static ...

    // Do not change code below


    public static int getMaxMinusCurrent(int val) {
        return Integer.MAX_VALUE - val;
    }

    public static void main(String[] args) {

        String vaild = "1 3";
        String notValid = "111";
        System.out.println(match(notValid));


    }
    public static boolean match (String input) {

        Pattern pattern = Pattern.compile("[1-3][[ \\t\\n\\x0b\\r\\f]][1-3]");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }
}