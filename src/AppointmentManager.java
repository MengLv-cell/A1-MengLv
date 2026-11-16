import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the full collection of appointments and all booking operations.
 * All validation and rejection logic lives in this class.
 * @author Meng Lv
 */
public class AppointmentManager {
    private ArrayList<Appointment> appointments;

    /**
     * Creates an empty appointment manager.
     */
    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    // ---------- private validation helpers ----------

    private boolean isValidProfessionalId(int id) {
        return id > 0;
    }

    private boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    private boolean isValidMobile(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isSlotAlreadyBooked(HealthProfessional pro, int slotMinutes) {
        for (Appointment app : appointments) {
            if (app.getProfessional().equals(pro)
                    && app.getTimeSlotMinutes() == slotMinutes
                    && app.getStatus().equals("Booked")) {
                return true;
            }
        }
        return false;
    }

    // ---------- public booking operations ----------

    /**
     * Validates and adds a new appointment.
     * Rejects invalid data, double bookings and non-working days.
     * @param patient patient record
     * @param professional chosen health professional
     * @param timeSlotMinutes appointment time in minutes from 08:00
     * @param day day of the week for working day check
     * @return true if booking was accepted, false if rejected
     */
    public boolean bookAppointment(Patient patient, HealthProfessional professional, int timeSlotMinutes, String day) {
        if (patient == null) {
            System.out.println("[WARNING] REJECTED: Patient cannot be null.");
            return false;
        }
        if (professional == null) {
            System.out.println("[WARNING] REJECTED: Health professional cannot be null.");
            return false;
        }
        if (!isValidName(patient.getFullName())) {
            System.out.println("[WARNING] REJECTED: Patient name is empty.");
            return false;
        }
        if (!isValidMobile(patient.getMobilePhone())) {
            System.out.println("[WARNING] REJECTED: Patient phone number is invalid.");
            return false;
        }
        if (!isValidProfessionalId(professional.getProfessionalId())) {
            System.out.println("[WARNING] REJECTED: Professional ID is invalid.");
            return false;
        }
        if (!isValidName(professional.getName())) {
            System.out.println("[WARNING] REJECTED: Professional name is empty.");
            return false;
        }
        if (!ClinicSchedule.isValidSlot(timeSlotMinutes)) {
            System.out.println("[WARNING] REJECTED: Time slot " + ClinicSchedule.formatTime(timeSlotMinutes) + " is outside schedule.");
            return false;
        }
        if (!professional.getAvailableDays().contains(day)) {
            System.out.println("[WARNING] REJECTED: " + professional.getName()
                    + " is not available on " + day + ".");
            return false;
        }
        if (isSlotAlreadyBooked(professional, timeSlotMinutes)) {
            System.out.println("[WARNING] REJECTED: Time slot "
                    + ClinicSchedule.formatTime(timeSlotMinutes)
                    + " is already booked for this professional.");
            return false;
        }

        Appointment newAppointment = new Appointment(patient, professional, timeSlotMinutes);
        appointments.add(newAppointment);
        System.out.println("[OK] Booking confirmed: " + newAppointment);
        return true;
    }

    // ---------- filtering ----------

    /**
     * Returns all active appointments for a given professional ID.
     * @param professionalId target professional ID
     * @return filtered list of appointments
     */
    public ArrayList<Appointment> filterByProfessionalId(int professionalId) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getProfessional().getProfessionalId() == professionalId
                    && app.getStatus().equals("Booked")) {
                result.add(app);
            }
        }
        return result;
    }

    /**
     * Returns all active appointments matching a patient phone number.
     * @param mobilePhone target phone number
     * @return filtered list of appointments
     */
    public ArrayList<Appointment> filterByPatientPhone(String mobilePhone) {
        ArrayList<Appointment> result = new ArrayList<>();
        if (mobilePhone == null) {
            System.out.println("[WARNING] Phone filter argument is null; returning empty list.");
            return result;
        }
        for (Appointment app : appointments) {
            if (mobilePhone.equals(app.getPatient().getMobilePhone())
                    && app.getStatus().equals("Booked")) {
                result.add(app);
            }
        }
        return result;
    }

    // ---------- sorting ----------

    /**
     * Sorts the provided appointment list by time, earliest first.
     * @param list list to sort
     */
    public void sortByTime(ArrayList<Appointment> list) {
        Collections.sort(list);
    }

    // ---------- cancellation (soft delete) ----------

    /**
     * Cancels an appointment by professional ID and time slot (soft delete).
     * @param professionalId target professional ID
     * @param timeSlotMinutes target time slot
     * @return true if cancelled, false if not found
     */
    public boolean cancelByProfessionalAndTime(int professionalId, int timeSlotMinutes) {
        for (Appointment app : appointments) {
            if (app.getProfessional().getProfessionalId() == professionalId
                    && app.getTimeSlotMinutes() == timeSlotMinutes
                    && app.getStatus().equals("Booked")) {
                app.cancel();
                System.out.println("[OK] Cancelled appointment for professional ID "
                        + professionalId + " at " + ClinicSchedule.formatTime(timeSlotMinutes));
                return true;
            }
        }
        System.out.println("[WARNING] No active appointment found for professional ID "
                + professionalId + " at " + ClinicSchedule.formatTime(timeSlotMinutes));
        return false;
    }

    /**
     * Cancels all active appointments matching a patient phone number (soft delete).
     * @param mobilePhone target phone number
     * @return true if at least one appointment was cancelled
     */
    public boolean cancelByPatientPhone(String mobilePhone) {
        if (mobilePhone == null) {
            System.out.println("[WARNING] Cannot cancel: phone argument is null.");
            return false;
        }
        boolean removed = false;
        for (Appointment app : appointments) {
            if (mobilePhone.equals(app.getPatient().getMobilePhone())
                    && app.getStatus().equals("Booked")) {
                app.cancel();
                removed = true;
            }
        }
        if (removed) {
            System.out.println("[OK] Cancelled all active appointments for phone: " + mobilePhone);
        } else {
            System.out.println("[WARNING] No active appointments found for phone: " + mobilePhone);
        }
        return removed;
    }

    // ---------- display helpers ----------

    /**
     * Prints all active appointments currently stored.
     */
    public void displayAllActiveAppointments() {
        int count = 0;
        for (Appointment app : appointments) {
            if (app.getStatus().equals("Booked")) count++;
        }
        System.out.println("----- Active Appointments (" + count + " total) -----");
        for (Appointment app : appointments) {
            if (app.getStatus().equals("Booked")) {
                System.out.println(app);
            }
        }
    }

    /**
     * Prints a provided list of appointments with a title.
     * @param list appointments to print
     * @param title section title
     */
    public void displayList(ArrayList<Appointment> list, String title) {
        System.out.println("----- " + title + " (" + list.size() + " items) -----");
        for (Appointment app : list) {
            System.out.println(app);
        }
    }
}
