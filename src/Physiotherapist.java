import java.util.ArrayList;

/**
 * Physiotherapist health professional type.
 * @author Meng Lv
 */
public class Physiotherapist extends HealthProfessional {
    private String treatmentFocus;

    public Physiotherapist(int professionalId, String name, ArrayList<String> availableDays, String treatmentFocus) {
        super(professionalId, name, availableDays);
        this.treatmentFocus = treatmentFocus;
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
