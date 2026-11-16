import java.util.ArrayList;

/**
 * Specialist health professional type.
 * @author Meng Lv
 */
public class Specialist extends HealthProfessional {
    private String subSpecialty;

    /**
     * Constructs a specialist professional.
     * @param professionalId numeric ID
     * @param name full name
     * @param availableDays working days
     * @param subSpecialty area of specialty expertise
     */
    public Specialist(int professionalId, String name, ArrayList<String> availableDays, String subSpecialty) {
        super(professionalId, name, availableDays);
        this.subSpecialty = subSpecialty;
    }

    @Override
    public String getRole() {
        return "Specialist";
    }

    /**
     * Returns the specialty area.
     * @return sub-specialty field
     */
    public String getSubSpecialty() {
        return subSpecialty;
    }

    /**
     * Updates the specialty area.
     * @param subSpecialty new expertise area
     */
    public void setSubSpecialty(String subSpecialty) {
        this.subSpecialty = subSpecialty;
    }

    /**
     * Prints a referral acceptance action for demonstration.
     * @param referringDoctor name of the referring GP
     */
    public void acceptReferral(String referringDoctor) {
        System.out.println("Dr. " + getName() + " accepts a referral from Dr. " + referringDoctor);
    }

    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + subSpecialty;
    }
}
