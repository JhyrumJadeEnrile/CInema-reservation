
    public class DateValidator {

        public static boolean isValidDay(int day, int month, int year) {
            int maxDays = 31;

            switch (month) {
                case 2:
                    maxDays = 28;
                    break;
                case 4: case 6: case 9: case 11:
                    maxDays = 30;
                    break;
                default:
                    maxDays = 31;
            }

            return day >= 1 && day <= maxDays;
        }
    }


