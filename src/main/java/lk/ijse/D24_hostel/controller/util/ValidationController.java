package lk.ijse.D24_hostel.controller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationController {

    public static boolean name(String name) {

        Pattern idPattern = Pattern.compile("^[A-z\\s]{4,15}$");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean address(String name) {
        Pattern idPattern = Pattern.compile("[A-z @ 0-9]{4,20}");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean PhoneNumber(String contact) {
        Pattern namePattern = Pattern.compile("^(?:7|0|(?:\\\\+94))[0-9]{9}$");
        Matcher matcher = namePattern.matcher(contact);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    public static boolean roomTypeId(String id) {
        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{4}$");
        boolean matches = idPattern.matcher(id).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean keyMoney(String salary) {
        Pattern idPattern = Pattern.compile("^[1-9][0-9]*(.[0-9]{2})?$");
        boolean matches = idPattern.matcher(salary).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean qty(String id) {
        Pattern idPattern = Pattern.compile("^[0-9]{1,3}$");
        boolean matches = idPattern.matcher(id).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean resId(String id) {
        Pattern idPattern = Pattern.compile("^(RES-)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean password(String pwd) {
        Pattern idPattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$");
        boolean matches = idPattern.matcher(pwd).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }

    }

    public static boolean userId(String id) {
        Pattern idPattern = Pattern.compile("^(user-)[0-9]{3}$");
        boolean matches = idPattern.matcher(id).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean userName(String name) {
        Pattern idPattern = Pattern.compile("^[a-zA-Z][a-zA-Z0-9-_]{4,32}$");
        boolean matches = idPattern.matcher(name).matches();
        if (matches) {
            return true;
        } else {
            return false;
        }
    }
}
