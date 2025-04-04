import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Unit tests for the hospital emergency system.
 */
public class PruebasUnitarias {

    /**
     * Test inserting and removing patients in VectorHeap.
     */
    @Test
    public void testVectorHeapInsertAndRemove() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        heap.add(new Paciente("Juan Perez", "fractura de pierna", 'C'));
        heap.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        heap.add(new Paciente("Lorenzo Toledo", "chikunguya", 'E'));

        // Checking order of attention
        assertEquals("Maria Ramirez, apendicitis, A", heap.remove().toString());
        assertEquals("Juan Perez, fractura de pierna, C", heap.remove().toString());
        assertEquals("Lorenzo Toledo, chikunguya, E", heap.remove().toString());
    }

    /**
     * Test inserting and removing patients in WrapperPriorityQueue.
     */
    @Test
    public void testWrapperPriorityQueueInsertAndRemove() {
        WrapperPriorityQueue<Paciente> queue = new WrapperPriorityQueue<>();
        queue.add(new Paciente("Carmen Sarmientos", "dolores de parto", 'B'));
        queue.add(new Paciente("Maria Ramirez", "apendicitis", 'A'));
        queue.add(new Paciente("Lorenzo Toledo", "chikunguya", 'E'));

        // Checking order of attention
        assertEquals("Maria Ramirez, apendicitis, A", queue.remove().toString());
        assertEquals("Carmen Sarmientos, dolores de parto, B", queue.remove().toString());
        assertEquals("Lorenzo Toledo, chikunguya, E", queue.remove().toString());
    }

    /**
     * Test reading patients from CSV file in Hospital.
     * @throws IOException if file cannot be created
     */
    @Test
    public void testHospitalReadCSV() throws IOException {
        // Create a temporary CSV file
        String filename = "test_pacientes.csv";
        FileWriter writer = new FileWriter(filename);
        writer.write("Juan Perez,fractura de pierna,C\n");
        writer.write("Maria Ramirez,apendicitis,A\n");
        writer.write("Lorenzo Toledo,chikunguya,E\n");
        writer.close();

        // Using VectorHeap for testing
        Hospital hospital = new Hospital(new VectorHeap<>());
        hospital.readCSV(filename);

        // Checking if patients were loaded correctly
        assertTrue(hospital.hasPatients());
        assertEquals("Maria Ramirez, apendicitis, A", hospital.attendPatient().toString());
        assertEquals("Juan Perez, fractura de pierna, C", hospital.attendPatient().toString());
        assertEquals("Lorenzo Toledo, chikunguya, E", hospital.attendPatient().toString());
    }
}
