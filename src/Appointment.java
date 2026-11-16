/**
 * Represents a single appointment linking a patient and a health professional at a time slot.
 * Time is stored as minutes offset from 08:00.
 * @author Meng Lv
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private HealthProfessional professional;
    private int timeSlotMinutes;
    private String status;

    /**
     * Constructs an appointment record with basic validation.
     * Invalid inputs fall back to safe defaults to avoid invalid state.
     * For full business validation, use AppointmentManager.bookAppointment().
     * @param patient the patient who booked
     * @param professional the chosen health professional
     * @param timeSlotMinutes appointment time as minutes from 08:00
     */
    public Appointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
        if (patient != null) {
            this.patient = patient;
        } else {
            this.patient = new Patient("Unknown Patient", "0000000000");
        }

        this.professional = professional;

        if (ClinicSchedule.isValidSlot(timeSlotMinutes)) {
            this.timeSlotMinutes = timeSlotMinutes;
        } else {
            this.timeSlotMinutes = ClinicSchedule.OPENING_MINUTES;
        }

        this.status = "Booked";
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
                + " | " + professional.getName()
                + " (" + professional.getRole() + ", ID:" + professional.getProfessionalId() + ")";
    }
}
