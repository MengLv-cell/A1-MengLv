/**
 * Represents a single appointment linking a patient and a health professional.
 * Objects can only be created via the static factory method.
 * Invalid inputs will return null and no object is created.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Private constructor: internal use only.
     * Callers must use the create() factory method to obtain instances.
     * @param patient valid patient object
     * @param professional valid health professional object
     * @param timeSlotMinutes valid time slot
     */
    private Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Static factory method to create a valid Appointment object.
     * Returns null if any input is invalid.
     * @param patient valid patient object, cannot be null
     * @param professional valid health professional, cannot be null
     * @param timeSlotMinutes valid 30-min slot within opening hours
     * @return valid Appointment object, or null if validation fails
     */
    public static Appointment create(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        if (patient == null || professional == null) {
            return null;
        }
        if (!ClinicSchedule.isValidSlot(timeSlotMinutes)) {
            return null;
        }
        return new Appointment(patient, professional, timeSlotMinutes);
    }

    public Patient getPatient() {
        return patient;
    }

    public HealthProfessional getProfessional() {
        return professional;
    }

    public int getTimeSlotMinutes() {
        return timeSlotMinutes;
    }

    public String getStatus() {
        return status;
    }

    public void cancel() {
        this.status = "Cancelled";
    }

    @Override
    public int compareTo(Appointment other) {
        return Integer.compare(this.timeSlotMinutes, other.timeSlotMinutes);
    }

    @Override
    public String toString() {
        return "[" + ClinicSchedule.formatTime(timeSlotMinutes) + "] [" + status + "] "
                + patient.toString()
                + " | " + professional.toString();
    }
}
