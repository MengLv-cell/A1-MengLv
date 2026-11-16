import java.util.ArrayList;

/**
 * Specialist health professional type.
 * Provides specialized medical services.
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
     * Returns null if core identity fields are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param subSpecialty area of specialty expertise
     * @return valid Specialist object, or null if validation fails
     */
    public static Specialist create(int professionalId, String name, ArrayList<String> availableDays, String subSpecialty) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        if (subSpecialty == null || subSpecialty.isBlank()) return null;
        return new Specialist(professionalId, name, availableDays, subSpecialty);
    }

    /**
     * Returns the role name of this professional.
     * @return role description
     */
    @Override
    public String getRole() {
        return "Specialist";
    }

    /**
     * Returns the specialty area of the specialist.
     * @return sub-specialty field
     */
    public String getSubSpecialty() {
        return subSpecialty;
    }

    /**
     * Updates the specialty area.
     * Blank or null values are ignored.
     * @param subSpecialty new expertise area
     */
    public void setSubSpecialty(String subSpecialty) {
        if (subSpecialty != null && !subSpecialty.isBlank()) {
            this.subSpecialty = subSpecialty;
        }
    }

    /**
     * Simulates accepting a referral from another doctor.
     * @param referringDoctor name of the referring GP
     */
    public void acceptReferral(String referringDoctor) {
        System.out.println("Dr. " + getName() + " accepts a referral from Dr. " + referringDoctor);
    }

    /**
     * Returns a formatted string with full specialist details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + subSpecialty;
    }
}
