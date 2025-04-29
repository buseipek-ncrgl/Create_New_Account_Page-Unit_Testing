package com.account;

public class Validator {

    public static boolean isValidName(String name) {
        return name != null && name.trim().length() >= 2;
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$");
    }

    public static boolean passwordsMatch(String pw, String cpw) {
        return pw != null && pw.equals(cpw);
    }

    public static boolean isStrongPassword(String pw) {
        if (pw == null) return false;
        return pw.length() >= 8 &&
               pw.matches(".*[a-z].*") &&               
               pw.matches(".*[A-Z].*") &&           
               pw.matches(".*[0-9].*") &&               
               pw.matches(".*[!@#$%^&*()_\\-+=.].*");   
    }

    public static String getPasswordErrorMessage(String pw) {
        if (pw == null || pw.trim().isEmpty()) {
            return "⚠ Password cannot be empty.";
        }
        if (pw.length() < 8) {
            return "⚠ Password must be at least 8 characters.";
        }
        if (!pw.matches(".*[a-z].*")) {
            return "⚠ Password must contain at least one lowercase letter.";
        }
        if (!pw.matches(".*[A-Z].*")) {
            return "⚠ Password must contain at least one uppercase letter.";
        }
        if (!pw.matches(".*[0-9].*")) {
            return "⚠ Password must contain at least one digit.";
        }
        if (!pw.matches(".*[!@#$%^&*()_\\-+=.].*")) {
            return "⚠ Password must contain at least one special character (!@#$%^&* etc.).";
        }
        return "";
    }
    

    public static boolean isValidDOB(String dob) {
        if (dob == null || !dob.matches("\\d{2}/\\d{2}/\\d{4}")) return false;

        String[] parts = dob.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (month < 1 || month > 12) return false;
        if (day < 1) return false;

        int[] daysInMonth = {
            31, 28, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
        };

        if (month == 2 && isLeapYear(year)) {
            if (day > 29) return false;
        } else {
            if (day > daysInMonth[month - 1]) return false;
        }

        return true;
    }

    private static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static boolean validateAccount(String firstName, String lastName, String email,
                                          String pw, String cpw, String dob) {
        return isValidName(firstName)
            && isValidName(lastName)
            && isValidEmail(email)
            && isStrongPassword(pw)
            && passwordsMatch(pw, cpw)
            && isValidDOB(dob);
    }
}
