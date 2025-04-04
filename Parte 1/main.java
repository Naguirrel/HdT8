import java.util.Scanner;

/**
 * Main class to run the hospital emergency system.
 */
public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IPriorityQueue<Paciente> queue;
        System.out.println("------------------------------------------------");
        System.out.println("Elija una de las siguientes opciones:");
        System.out.println("1. VectorHeap (Custom Heap)");
        System.out.println("2. PriorityQueue");
        System.out.print("Escoge uno (1 or 2): ");
        int choice = scanner.nextInt();
        System.out.println("------------------------------------------------");
        // Create appropriate queue
        if (choice == 1) {
            queue = new VectorHeap<>();
        } else {
            queue = new WrapperPriorityQueue<>();
        }

        Hospital hospital = new Hospital(queue);
        hospital.readCSV("pacientes.txt");
        Paciente p;
        while ((p = hospital.attendPatient()) != null) {
            System.out.println(p);
        }

        scanner.close();
    }
}
