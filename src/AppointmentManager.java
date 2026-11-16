import java.util.ArrayList;
import java.util.Collections;

/**
 * Manages the full collection of appointments and all booking operations.
 * @author Meng Lv
 */
public class AppointmentManager {
    private ArrayList<Appointment> appointments;

    public AppointmentManager() {
        appointments = new ArrayList<>();
    }

    // ---------- validation helpers ----------
    private boolean isValidProfessionalId(int id) {
        return id > 0;
    }

    private boolean isValidName(String name) {
        return name != null && name.trim().length() > 0;
    }

    private boolean isValidMobile(String phone) {
        return phone != null && phone.length() == 10;
    }

    private boolean isSlotAlreadyBooked(HealthProfessional pro, int slotMinutes) {
        for (Appointment app : appointments) {
            if (app.getProfessional().equals(pro) && app.getTimeSlotMinutes() == slotMinutes) {
                return true;
            }
        }
        return false;
    }

    // ---------- booking ----------
    public boolean bookAppointment(Patient patient, HealthProfessional professional, int timeSlotMinutes) {
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
        if (isSlotAlreadyBooked(professional, timeSlotMinutes)) {
            System.out.println("[WARNING] REJECTED: Double booking for professional ID "
                    + professional.getProfessionalId()
                    + " at " + ClinicSchedule.formatTime(timeSlotMinutes));
            return false;
        }

        Appointment newAppointment = new Appointment(patient, professional, timeSlotMinutes);
        appointments.add(newAppointment);
        System.out.println("[OK] Booking confirmed: " + newAppointment);
        return true;
    }

    // ---------- filtering ----------
    public ArrayList<Appointment> filterByProfessionalId(int professionalId) {
        ArrayList<Appointment> result = new ArrayList<>();
        for (Appointment app : appointments) {
            if (app.getProfessional().getProfessionalId() == professionalId) {
                result.add(app);
            }
        }
        return result;
    }

    public ArrayList<Appointment> filterByPatientPhone(String mobilePhone) {
        ArrayList<Appointment> result = new ArrayList<>();
        if (mobilePhone == null) {
            System.out.println("[WARNING] Phone filter argument is null; returning empty list.");
            return result;
        }
        for (Appointment app : appointments) {
            if (mobilePhone.equals(app.getPatient().getMobilePhone())) {
                result.add(app);
            }
        }
        return result;
    }

    // ---------- sorting ----------
    public void sortByTime(ArrayList<Appointment> list) {
        Collections.sort(list);
    }

    // ---------- cancellation (physical delete) ----------
    public boolean cancelByProfessionalAndTime(int professionalId, int timeSlotMinutes) {
        for (int i = 0; i < appointments.size(); i++) {
            Appointment app = appointments.get(i);
            if (app.getProfessional().getProfessionalId() == professionalId
                    && app.getTimeSlotMinutes() == timeSlotMinutes) {
                appointments.remove(i);
                System.out.println("[OK] Cancelled appointment for professional ID "
                        + professionalId + " at " + ClinicSchedule.formatTime(timeSlotMinutes));
                return true;
            }
        }
        System.out.println("[WARNING] No appointment found for professional ID "
                + professionalId + " at " + ClinicSchedule.formatTime(timeSlotMinutes));
        return false;
    }

    public boolean cancelByPatientPhone(String mobilePhone) {
        if (mobilePhone == null) {
            System.out.println("[WARNING] Cannot cancel: phone argument is null.");
            return false;
        }
        boolean removed = false;
        for (int i = appointments.size() - 1; i >= 0; i--) {
            if (mobilePhone.equals(appointments.get(i).getPatient().getMobilePhone())) {
                appointments.remove(i);
                removed = true;
            }
        }
        if (removed) {
            System.out.println("[OK] Cancelled all appointments for phone: " + mobilePhone);
        } else {
            System.out.println("[WARNING] No appointments found for phone: " + mobilePhone);
        }
        return removed;
    }

    // ---------- display ----------
    public void displayAllAppointments() {
        System.out.println("----- All Appointments (" + appointments.size() + " total) -----");
        for (Appointment app : appointments) {
            System.out.println(app);
        }
    }

    public void displayList(ArrayList<Appointment> list, String title) {
        System.out.println("----- " + title + " (" + list.size() + " items) -----");
        for (Appointment app : list) {
            System.out.println(app);
        }
    }
}
