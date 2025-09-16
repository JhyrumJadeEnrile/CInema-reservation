class reservationDetails {
    private String name;
    private String email;
    private int seatNumber;
    private int day;
    private int month;
    private int year;
    private String movieTitle;

    public reservationDetails(String name, String email, int seatNumber, int day, int month, int year, String movieTitle) {
        this.name = name;
        this.email = email;
        this.seatNumber = seatNumber;
        this.day = day;
        this.month = month;
        this.year = year;
        this.movieTitle = movieTitle;
    }

    // Getters for all fields
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
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
    public String getMovieTitle() {
        return movieTitle;
    }

    // Setters for updating
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber; }
    public void setDay(int day) { this.day = day; }
    public void setMonth(int month) { this.month = month; }
    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Name: " + name + ", Email: " + email + ", Seat: " + seatNumber + ", Date: " + month + "/" + day + "/" + year;
    }
}

