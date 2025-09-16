// Utility class for validating dates
class DateValidator {
    // Check if a year is a leap year
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Check if a day is valid for a given month and year
    public static boolean isValidDay(int day, int month, int year) {
        if (day < 1) return false;

        switch (month) {
            case 2:
                return isLeapYear(year) ? day <= 29 : day <= 28;
            case 4: case 6: case 9: case 11:
                return day <= 30;
            default:
                return day <= 31;
        }
    }
}
