import java.util.ArrayList;

/**
 * Dietitian health professional type.
 * Provides nutrition advice and personalised meal plans.
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

    /**
     * Returns the role name of this professional.
     * Overrides the abstract method from the parent class.
     * @return role description
     */
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
     * Simulates creating a personalised meal plan for demonstration.
     * @param patientName name of the patient receiving the plan
     */
    public void createMealPlan(String patientName) {
        System.out.println("Dietitian " + getName() + " creates a " + nutritionFocus + " meal plan for " + patientName);
    }

    /**
     * Returns a formatted string with full dietitian details.
     * Extends the parent toString with subclass-specific information.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
