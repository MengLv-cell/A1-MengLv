import java.util.ArrayList;

/**
 * Specialist health professional type.
 * @author Meng Lv
 */
public class Specialist extends HealthProfessional {
    private String subSpecialty;

    private Specialist(int professionalId, String name, ArrayList<String> availableDays, String subSpecialty) {
        super(professionalId, name, availableDays);
        this.subSpecialty = subSpecialty;
    }

    /**
     * Static factory method to create a valid Specialist object.
     * Returns null if core inputs are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param subSpecialty area of expertise
     * @return valid Specialist object, or null if validation fails
     */
    public static Specialist create(int professionalId, String name, ArrayList<String> availableDays, String subSpecialty) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        return new Specialist(professionalId, name, availableDays, subSpecialty);
    }

    @Override
    public String getRole() {
        return "Specialist";
    }

    public String getSubSpecialty() {
        return subSpecialty;
    }

    public void setSubSpecialty(String subSpecialty) {
        this.subSpecialty = subSpecialty;
    }

    public void acceptReferral(String referringDoctor) {
        System.out.println("Dr. " + getName() + " accepts a referral from Dr. " + referringDoctor);
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + subSpecialty;
    }
}
