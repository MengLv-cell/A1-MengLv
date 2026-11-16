import java.util.ArrayList;

/**
 * Physiotherapist health professional type.
 * Provides physical therapy and rehabilitation services.
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
     * Returns null if core fields are invalid or no working days are provided.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param treatmentFocus area of physical therapy
     * @return valid Physiotherapist object, or null if validation fails
     */
    public static Physiotherapist create(int professionalId, String name, ArrayList<String> availableDays, String treatmentFocus) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        if (treatmentFocus == null || treatmentFocus.isBlank()) return null;

        ArrayList<String> cleanedDays = HealthProfessional.cleanWeekdayList(availableDays);
        if (cleanedDays.isEmpty()) return null;

        return new Physiotherapist(professionalId, name, availableDays, treatmentFocus);
    }

    /**
     * Returns the role name of this professional.
     * @return role description
     */
    @Override
    public String getRole() {
        return "Physiotherapist";
    }

    /**
     * Returns the physical therapy focus area.
     * @return treatment focus area
     */
    public String getTreatmentFocus() {
        return treatmentFocus;
    }

    /**
     * Updates the physical therapy focus area.
     * Blank or null values are ignored.
     * @param treatmentFocus new treatment focus
     */
    public void setTreatmentFocus(String treatmentFocus) {
        if (treatmentFocus != null && !treatmentFocus.isBlank()) {
            this.treatmentFocus = treatmentFocus;
        }
    }

    /**
     * Simulates a physical therapy session action.
     * @param patientName name of the patient receiving therapy
     */
    public void conductTherapySession(String patientName) {
        System.out.println("Physiotherapist " + getName() + " conducts " + treatmentFocus + " therapy for " + patientName);
    }

    /**
     * Returns a formatted string with full physiotherapist details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Treatment focus: " + treatmentFocus;
    }
}
