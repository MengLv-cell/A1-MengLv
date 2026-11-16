import java.util.ArrayList;

/**
 * Specialist health professional type.
 * @author Meng Lv
 */
public class Specialist extends HealthProfessional {
    private String subSpecialty;

    public Specialist(int professionalId, String name, ArrayList<String> availableDays, String subSpecialty) {
        super(professionalId, name, availableDays);
        this.subSpecialty = subSpecialty;
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
        System.out.println("Dr. " + getName() + " accepts referral from " + referringDoctor);
    }

    @Override
    public String toString() {
        return super.toString() + " | Sub-specialty: " + subSpecialty;
    }
}
