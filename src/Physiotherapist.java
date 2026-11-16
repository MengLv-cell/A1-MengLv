import java.util.ArrayList;

/**
 * Physiotherapist health professional type.
 * @author Meng Lv
 */
public class Physiotherapist extends HealthProfessional {
    private String treatmentFocus;

    private Physiotherapist(int professionalId, String name, ArrayList<String> availableDays, String treatmentFocus) {
        super(professionalId, name, availableDays);
        this.treatmentFocus = treatmentFocus;
    }

    /**
     * Static factory method to create a valid Physiotherapist object.
     * Returns null if core inputs are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param treatmentFocus area of therapy expertise
     * @return valid Physiotherapist object, or null if validation fails
     */
    public static Physiotherapist create(int professionalId, String name, ArrayList<String> availableDays, String treatmentFocus) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        return new Physiotherapist(professionalId, name, availableDays, treatmentFocus);
    }

    @Override
    public String getRole() {
        return "Physiotherapist";
    }

    public String getTreatmentFocus() {
        return treatmentFocus;
    }

    public void setTreatmentFocus(String treatmentFocus) {
        this.treatmentFocus = treatmentFocus;
    }

    public void conductTherapySession(String patientName) {
        System.out.println("Physiotherapist " + getName() + " conducts " + treatmentFocus + " therapy for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Treatment focus: " + treatmentFocus;
    }
}
