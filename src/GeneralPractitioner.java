import java.util.ArrayList;

/**
 * General Practitioner (GP) health professional type.
 * @author Meng Lv
 */
public class GeneralPractitioner extends HealthProfessional {
    private boolean canPrescribeMedication;

    public GeneralPractitioner(int professionalId, String name, ArrayList<String> availableDays, boolean canPrescribeMedication) {
        super(professionalId, name, availableDays);
        this.canPrescribeMedication = canPrescribeMedication;
    }

    @Override
    public String getRole() {
        return "General Practitioner";
    }

    public boolean canPrescribeMedication() {
        return canPrescribeMedication;
    }

    public void setCanPrescribeMedication(boolean canPrescribeMedication) {
        this.canPrescribeMedication = canPrescribeMedication;
    }

    public void prescribeMedication(String medication) {
        System.out.println("Dr. " + getName() + " prescribes: " + medication);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can prescribe: " + canPrescribeMedication;
    }
}
