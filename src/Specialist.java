import java.util.ArrayList;

/**
 * Specialist health professional type.
 * Provides specialized medical services and accepts referrals from GPs.
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

    /**
     * Returns the role name of this professional.
     * Overrides the abstract method from the parent class.
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
     * @param subSpecialty new expertise area
     */
    public void setSubSpecialty(String subSpecialty) {
        this.subSpecialty = subSpecialty;
    }

    /**
     * Simulates accepting a referral from another doctor for demonstration.
     * @param referringDoctor name of the referring GP
     */
    public void acceptReferral(String referringDoctor) {
        System.out.println("Dr. " + getName() + " accepts a referral from Dr. " + referringDoctor);
    }

    /**
     * Returns a formatted string with full specialist details.
     * Extends the parent toString with subclass-specific information.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Specialty: " + subSpecialty;
    }
}
