import java.util.ArrayList;

/**
 * Nurse health professional type.
 * @author Meng Lv
 */
public class Nurse extends HealthProfessional {
    private boolean canTakeBloodSample;

    public Nurse(int professionalId, String name, ArrayList<String> availableDays, boolean canTakeBloodSample) {
        super(professionalId, name, availableDays);
        this.canTakeBloodSample = canTakeBloodSample;
    }

    @Override
    public String getRole() {
        return "Nurse";
    }

    public boolean canTakeBloodSample() {
        return canTakeBloodSample;
    }

    public void setCanTakeBloodSample(boolean canTakeBloodSample) {
        this.canTakeBloodSample = canTakeBloodSample;
    }

    public void collectBloodSample(String patientName) {
        System.out.println("Nurse " + getName() + " collects blood sample from " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Can take blood: " + canTakeBloodSample;
    }
}
