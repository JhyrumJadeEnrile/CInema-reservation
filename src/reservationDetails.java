public class reservationDetails extends person{
    private int seatNumber;
    private int day;
    private int month;
    private int year;

    public reservationDetails(String name, String email, int seatNumber, int day, int month, int year) {
        super(name, email);
        this.seatNumber = seatNumber;
        this.day = day;
        this.month = month;
        this.year = year;
    }
    // Getters
    public int getSeatNumber() {
        return seatNumber;
    }
    public int getDay() {
        return day;
    }
    public int getMonth() {
        return month;
    }
    public int getYear() {
        return year;
    }
    // Setters
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public void setDay(int day) {
        this.day = day;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public void personalInfo(){
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Seat Number: " + seatNumber);
        System.out.println("Date: " + day + "/" + month + "/" + year);
    }

}
