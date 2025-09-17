// Class representing a reservation, extends person
public class reservationDetails extends person {
    private int seatNumber;
    private int day;
    private int month;
    private int year;
    private String movieTitle;

    // Constructor
    public reservationDetails(String name, String email, int seatNumber,
                              int day, int month, int year, String movieTitle) {
        super(name, email); // Call parent constructor
        this.seatNumber = seatNumber;
        this.day = day;
        this.month = month;
        this.year = year;
        this.movieTitle = movieTitle;
    }

    // Getters
    public int getSeatNumber() {
        return seatNumber; }
    public int getDay() {
        return day; }
    public int getMonth() {
        return month; }
    public int getYear() {
        return year; }
    public String getMovieTitle() {
        return movieTitle; }

    // Setters
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber; }
    public void setDay(int day) {
        this.day = day; }
    public void setMonth(int month) {
        this.month = month; }
    public void setYear(int year) {
        this.year = year; }

    @Override
    public String toString() {
        return "Name: " + getName() +
                ", Email: " + getEmail() +
                ", Seat: " + seatNumber +
                ", Date: " + month + "/" + day + "/" + year +
                ", Movie: " + movieTitle;
    }


}
