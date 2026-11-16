import java.util.ArrayList;

/**
 * Dietitian health professional type.
 * @author Meng Lv
 */
public class Dietitian extends HealthProfessional {
    private String nutritionFocus;

    /**
     * Constructs a dietitian professional.
     * @param professionalId numeric ID
     * @param name full name
     * @param availableDays working days
     * @param nutritionFocus area of nutrition expertise
     */
    public Dietitian(int professionalId, String name, ArrayList<String> availableDays, String nutritionFocus) {
        super(professionalId, name, availableDays);
        this.nutritionFocus = nutritionFocus;
    }

    @Override
    public String getRole() {
        return "Dietitian";
    }

    /**
     * Returns the nutrition focus area.
     * @return nutrition expertise area
     */
    public String getNutritionFocus() {
        return nutritionFocus;
    }

    /**
     * Updates the nutrition focus area.
     * @param nutritionFocus new expertise area
     */
    public void setNutritionFocus(String nutritionFocus) {
        this.nutritionFocus = nutritionFocus;
    }

    /**
     * Prints a meal plan creation action for demonstration.
     * @param patientName name of the patient
     */
    public void createMealPlan(String patientName) {
        System.out.println("Dietitian " + getName() + " creates a " + nutritionFocus + " meal plan for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
