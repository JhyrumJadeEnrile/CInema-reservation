import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class fileHandler {
    
    private static final String FILE_NAME = "reserved.txt";

    public void saveReservations(List<reservationDetails> reservations) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (reservationDetails r : reservations) {
                String line = r.getName() + "," + r.getEmail() + "," + r.getSeatNumber() + "," + r.getDay() + "," + r.getMonth() + "," + r.getYear();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving reservations: " + e.getMessage());
        }
    }

    public List<reservationDetails> loadReservations() {
    List<reservationDetails> reservations = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                String name = parts[0];
                String email = parts[1];
                int seatNumber = Integer.parseInt(parts[2]);
                int day = Integer.parseInt(parts[3]);
                int month = Integer.parseInt(parts[4]);
                int year = Integer.parseInt(parts[5]);
                reservationDetails r = new reservationDetails(name, email, seatNumber, day, month, year);
                reservations.add(r);
            }
        }
    } catch (FileNotFoundException e) {
        System.out.println("No existing reservations found.");
    } catch (IOException e) {
        System.out.println("Error loading reservations: " + e.getMessage());
    }
    return new ArrayList<>(reservations);
}






}
