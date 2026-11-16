import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main entry class for PROG2004 Assignment 1.
 * All valid bookings are for the same day (Monday).
 * Demonstrates full system features and all error test cases.
 * @author Meng Lv
 */
public class AssignmentOne {
    public static void main(String[] args) {
        AppointmentManager manager = new AppointmentManager();

        // ========== Part 3: Health Professional Team ==========
        System.out.println("==================================================");
        System.out.println("PART 3: Health Professional Team");
        System.out.println("==================================================");

        HealthProfessional gp1 = new GeneralPractitioner(101, "Anna Miller",
                new ArrayList<>(Arrays.asList("Monday", "Wednesday", "Friday")), true);
        HealthProfessional gp2 = new GeneralPractitioner(102, "Ben Carter",
                new ArrayList<>(Arrays.asList("Tuesday", "Thursday")), true);
        HealthProfessional gp3 = new GeneralPractitioner(103, "Clara Wilson",
                new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Thursday", "Friday")), true);
        HealthProfessional spec1 = new Specialist(201, "David Moore",
                new ArrayList<>(Arrays.asList("Wednesday", "Thursday")), "Cardiology");
        HealthProfessional nurse1 = new Nurse(301, "Emma Taylor",
                new ArrayList<>(Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")), true);
        HealthProfessional diet1 = new Dietitian(401, "Fiona Brown",
                new ArrayList<>(Arrays.asList("Tuesday", "Friday")), "Sports Nutrition");
        HealthProfessional physio1 = new Physiotherapist(501, "Grace Hall",
                new ArrayList<>(Arrays.asList("Monday", "Wednesday", "Friday")), "Sports Injury");

        ArrayList<HealthProfessional> staff = new ArrayList<>();
        staff.add(gp1);
        staff.add(gp2);
        staff.add(gp3);
        staff.add(spec1);
        staff.add(nurse1);
        staff.add(diet1);
        staff.add(physio1);

        for (HealthProfessional hp : staff) {
            System.out.println(hp);
        }
        System.out.println();

        // ========== Part 6: Normal Booking Workflow (Monday only) ==========
        System.out.println("==================================================");
        System.out.println("PART 6: Normal Booking Workflow (Monday)");
        System.out.println("==================================================");

        Patient p1 = new Patient("James Lee", "0412345678");
        Patient p2 = new Patient("Sophie Wang", "0498765432");
        Patient p3 = new Patient("Liam Davis", "0422111333");

        // Added out of order to clearly show sorting effect later
        manager.bookAppointment(p2, gp1, 180, "Monday"); // 11:00
        manager.bookAppointment(p1, gp1, 0, "Monday");   // 08:00
        manager.bookAppointment(p3, gp1, 60, "Monday");  // 09:00
        manager.bookAppointment(p1, nurse1, 120, "Monday"); // 10:00
        manager.bookAppointment(p2, diet1, 240, "Monday");  // 12:00
        manager.bookAppointment(p3, physio1, 300, "Monday");// 13:00
        System.out.println();

        manager.displayAllActiveAppointments();
        System.out.println();

        // E1: Double booking test (run BEFORE cancellation)
        System.out.println("[TEST E1] Double booking attempt: GP101 at 09:00 Monday");
        manager.bookAppointment(p3, gp1, 60, "Monday");
        System.out.println();

        // Filter result is naturally out of order
        ArrayList<Appointment> byPro = manager.filterByProfessionalId(101);
        manager.displayList(byPro, "Filtered: Professional ID 101 (before sort)");
        System.out.println();

        // Sort demonstration: out-of-order -> ascending
        manager.sortByTime(byPro);
        manager.displayList(byPro, "Sorted: Professional ID 101 by time");
        System.out.println();

        ArrayList<Appointment> byPhone = manager.filterByPatientPhone("0412345678");
        manager.displayList(byPhone, "Filtered: Patient phone 0412345678");
        System.out.println();

        // Cancellation tests
        manager.cancelByProfessionalAndTime(101, 0);
        manager.cancelByPatientPhone("0498765432");
        System.out.println();

        System.out.println("--- Appointments after cancellations ---");
        manager.displayAllActiveAppointments();
        System.out.println();

        // ========== Part 6: Error & Rejection Test Cases ==========
        System.out.println("==================================================");
        System.out.println("PART 6: Error & Rejection Test Cases");
        System.out.println("==================================================");

        // E2: Cancel non-existent appointment
        System.out.println("[TEST E2] Cancel non-existent appointment");
        manager.cancelByProfessionalAndTime(999, 0);
        System.out.println();

        // E3: Cancel with unknown phone number
        System.out.println("[TEST E3] Cancel with unknown phone number");
        manager.cancelByPatientPhone("0400000000");
        System.out.println();

        // E4: Time slot after last valid slot
        System.out.println("[TEST E4] Booking at 16:00 (after last valid slot 15:30)");
        manager.bookAppointment(p1, gp2, 480, "Tuesday");
        System.out.println();

        // E5: Non-30-minute time slot
        System.out.println("[TEST E5] Booking at 08:15 (not aligned to 30-min slot)");
        manager.bookAppointment(p1, gp2, 15, "Tuesday");
        System.out.println();

        // E6: Invalid patient phone (non-digit characters)
        System.out.println("[TEST E6] Patient phone with non-digit characters");
        Patient badPhonePatient = new Patient("Tom Green", "abcdefghij");
        manager.bookAppointment(badPhonePatient, gp2, 30, "Tuesday");
        System.out.println();

        // E7: Null patient object
        System.out.println("[TEST E7] Booking with null patient");
        manager.bookAppointment(null, gp2, 30, "Tuesday");
        System.out.println();

        // E8: Filter with null phone argument
        System.out.println("[TEST E8] Filter with null phone argument");
        manager.filterByPatientPhone(null);
        System.out.println();

        // E9: Cancel with null phone argument
        System.out.println("[TEST E9] Cancel with null phone argument");
        manager.cancelByPatientPhone(null);
        System.out.println();

        // E10: Non-working day (tests working day validation rule)
        System.out.println("[TEST E10] Booking on a non-working day (GP2 does not work Monday)");
        manager.bookAppointment(p1, gp2, 60, "Monday");
        System.out.println();

        System.out.println("==================================================");
        System.out.println("End of full demonstration");
        System.out.println("==================================================");
    }
}
