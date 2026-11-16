import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages appointment booking lifecycle and business-level validation.
 * Entity classes handle their own basic state validation.
 * This class enforces booking rules, duplicate checks and working day constraints.
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

    /**
     * Checks if a professional ID is a positive integer.
     * @param id ID to check
     * @return true if valid
     */
    private boolean isValidProfessionalId(int id) {
        return id > 0;
    }

    /**
     * Checks if a name string is not blank.
     * @param name name to check
     * @return true if valid
     */
    private boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    /**
     * Checks if a phone number is a valid 10-digit numeric string.
     * @param phone phone number to check
     * @return true if valid
     */
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

    /**
     * Checks if a time slot is already booked by an active appointment.
     * @param pro target health professional
     * @param slotMinutes target time slot
     * @return true if the slot is already booked
     */
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
     * Validates and creates a new appointment.
     * Rejects invalid data, double bookings and non-working days.
     * @param patient patient record
     * @param professional assigned health professional
     * @param timeSlotMinutes appointment start time in minutes from 08:00
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

        Appointment newAppointment = Appointment.create(patient, professional, timeSlotMinutes);
        if (newAppointment == null) {
            System.out.println("[WARNING] REJECTED: Appointment cannot be created with invalid data.");
            return false;
        }

        appointments.add(newAppointment);
        System.out.println("[OK] Booking confirmed: " + newAppointment);
        return true;
    }

    // ---------- filtering ----------

    /**
     * Returns all active appointments for a given professional ID.
     * @param professionalId target professional ID
     * @return filtered list of active appointments
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
     * @return filtered list of active appointments
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
     * Sorts the provided appointment list by start time, earliest first.
     * @param list list to sort
     */
    public void sortByTime(ArrayList<Appointment> list) {
        Collections.sort(list);
    }

    // ---------- cancellation (soft delete) ----------

    /**
     * Cancels an active appointment by professional ID and time slot.
     * Uses soft delete: changes status, does not remove the record.
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
     * Cancels all active appointments matching a patient phone number.
     * Uses soft delete: changes status, does not remove records.
     * @param mobilePhone target phone number
     * @return true if at least one appointment was cancelled
     */
    public boolean cancelByPatientPhone(String mobilePhone) {
        if (mobilePhone == null) {
            System.out.println("[WARNING] Cannot cancel: phone argument is null.");
            return false;
        }
        boolean cancelled = false;
        for (Appointment app : appointments) {
            if (mobilePhone.equals(app.getPatient().getMobilePhone())
                    && app.getStatus().equals("Booked")) {
                app.cancel();
                cancelled = true;
            }
        }
        if (cancelled) {
            System.out.println("[OK] Cancelled all active appointments for phone: " + mobilePhone);
        } else {
            System.out.println("[WARNING] No active appointments found for phone: " + mobilePhone);
        }
        return cancelled;
    }

    // ---------- display helpers ----------

    /**
     * Prints all currently active appointments.
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
     * Prints a list of appointments with a section title.
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
