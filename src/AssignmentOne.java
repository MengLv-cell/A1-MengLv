import java.util.ArrayList;
import java.util.Arrays;

/**
 * Main entry class for PROG2004 Assignment 1.
 * Demonstrates the full appointment system.
 * @author Meng Lv
 */
public class AssignmentOne {
    public static void main(String[] args) {
        AppointmentManager manager = new AppointmentManager();

        // ========== Part 3: Health Professionals ==========
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

        ArrayList<HealthProfessional> staff = new ArrayList<>();
        staff.add(gp1);
        staff.add(gp2);
        staff.add(gp3);
        staff.add(spec1);
        staff.add(nurse1);
        staff.add(diet1);

        for (HealthProfessional hp : staff) {
            System.out.println(hp);
        }
        System.out.println();

        // ========== Part 6: Normal booking flow ==========
        System.out.println("==================================================");
        System.out.println("PART 6: Normal Booking Workflow");
        System.out.println("==================================================");

        Patient p1 = new Patient("James Lee", "0412345678");
        Patient p2 = new Patient("Sophie Wang", "0498765432");
        Patient p3 = new Patient("Liam Davis", "0422111333");

        manager.bookAppointment(p1, gp1, 0);
        manager.bookAppointment(p2, gp1, 60);
        manager.bookAppointment(p3, spec1, 120);
        manager.bookAppointment(p1, nurse1, 180);
        manager.bookAppointment(p2, diet1, 240);
        System.out.println();

        manager.displayAllAppointments();
        System.out.println();

        // Filter tests
        ArrayList<Appointment> byPro = manager.filterByProfessionalId(101);
        manager.displayList(byPro, "Filtered: Professional ID 101");
        System.out.println();

        ArrayList<Appointment> byPhone = manager.filterByPatientPhone("0412345678");
        manager.displayList(byPhone, "Filtered: Patient phone 0412345678");
        System.out.println();

        // Sort test
        manager.sortByTime(byPro);
        manager.displayList(byPro, "Sorted: Professional ID 101 by time");
        System.out.println();

        // Cancellation tests
        manager.cancelByProfessionalAndTime(101, 0);
        manager.cancelByPatientPhone("0498765432");
        System.out.println();

        System.out.println("--- Appointments after cancellations ---");
        manager.displayAllAppointments();
        System.out.println();

        System.out.println("==================================================");
        System.out.println("End of basic demonstration");
        System.out.println("==================================================");
    }
}
