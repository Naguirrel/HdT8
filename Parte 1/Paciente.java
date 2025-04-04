/**
 * Represents a patient in the hospital emergency system.
 */
public class Paciente implements Comparable<Paciente> {
    private String name;
    private String symptom;
    private char priority;

    /**
     * Constructs a new patient.
     *
     * @param name     The patient's name.
     * @param symptom  The patient's symptom.
     * @param priority The emergency priority (A = highest, E = lowest).
     */
    public Paciente(String name, String symptom, char priority) {
        this.name = name;
        this.symptom = symptom;
        this.priority = priority;
    }

    /**
     * Compares two patients based on priority.
     *
     * @param other The other patient to compare.
     * @return Negative if this patient has higher priority, positive otherwise.
     */
    @Override
    public int compareTo(Paciente other) {
        return Character.compare(this.priority, other.priority); // A < B < C < ...
    }

    @Override
    public String toString() {
        return name + ", " + symptom + ", " + priority;
    }
}
