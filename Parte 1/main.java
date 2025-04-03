import java.util.Scanner;

/**
 * Main class to test the hospital emergency system.
 */
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hospital hospital = new Hospital();

        // Read patients from CSV file
        String filename = "pacientes.txt";
        hospital.readCSV(filename);

        // Attend patients in priority order
        System.out.println("\nAttending patients:");
        while (hospital.hasPatients()) {
            System.out.println(hospital.attendPatient());
        }

        scanner.close();
    }
}
