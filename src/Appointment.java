/**
 * Represents a single appointment linking a patient and a health professional.
 * Time is stored as minutes offset from 08:00.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Constructs an appointment record.
     * Callers must validate all inputs before creating an appointment.
     * @param patient the patient who booked
     * @param professional the assigned health professional
     * @param timeSlotMinutes appointment start time in minutes from 08:00
     */
    public Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        this.patient = patient;
        this.professional = professional;
        this.timeSlotMinutes = timeSlotMinutes;
        this.status = "Booked";
    }

    /**
     * Returns the patient of this appointment.
     * @return patient object
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Returns the assigned health professional.
     * @return health professional object
     */
    public HealthProfessional getProfessional() {
        return professional;
    }

    /**
     * Returns the appointment start time in minutes from 08:00.
     * @return time slot in minutes
     */
    public int getTimeSlotMinutes() {
        return timeSlotMinutes;
    }

    /**
     * Returns the current status of the appointment.
     * @return appointment status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Marks the appointment as cancelled (soft delete).
     */
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
