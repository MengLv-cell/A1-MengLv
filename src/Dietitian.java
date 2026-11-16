import java.util.ArrayList;

/**
 * Dietitian health professional type.
 * @author Meng Lv
 */
public class Dietitian extends HealthProfessional {
    private String nutritionFocus;

    public Dietitian(int professionalId, String name, ArrayList<String> availableDays, String nutritionFocus) {
        super(professionalId, name, availableDays);
        this.nutritionFocus = nutritionFocus;
    }

    @Override
    public String getRole() {
        return "Dietitian";
    }

    public String getNutritionFocus() {
        return nutritionFocus;
    }

    public void setNutritionFocus(String nutritionFocus) {
        this.nutritionFocus = nutritionFocus;
    }

    public void createMealPlan(String patientName) {
        System.out.println("Dietitian " + getName() + " creates a meal plan for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
