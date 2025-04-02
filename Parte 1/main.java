import java.util.Scanner;

/**
 * Main class to test the hospital emergency system.
 */
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user which priority queue implementation to use
        System.out.println("Choose priority queue implementation:");
        System.out.println("1. VectorHeap (Custom Heap)");
        System.out.println("2. WrapperPriorityQueue (Java's built-in PriorityQueue)");
        System.out.print("Enter choice (1 or 2): ");
        int choice = scanner.nextInt();
        boolean useVectorHeap = (choice == 1);

        // Create hospital with chosen priority queue
        Hospital hospital = new Hospital(useVectorHeap);

        // Read patients from CSV file
        String filename = "pacientes.txt";
        hospital.readCSV(filename);

        // Attend patients in priority order
        System.out.println("\nAttending patients:");
        Paciente patient;
        while ((patient = hospital.attendPatient()) != null) {
            System.out.println(patient);
        }

        scanner.close();
    }
}
