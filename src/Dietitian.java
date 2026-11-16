import java.util.ArrayList;

/**
 * Dietitian health professional type.
 * @author Meng Lv
 */
public class Dietitian extends HealthProfessional {
    private String nutritionFocus;

    private Dietitian(int professionalId, String name, ArrayList<String> availableDays, String nutritionFocus) {
        super(professionalId, name, availableDays);
        this.nutritionFocus = nutritionFocus;
    }

    /**
     * Static factory method to create a valid Dietitian object.
     * Returns null if core inputs are invalid.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param nutritionFocus area of expertise
     * @return valid Dietitian object, or null if validation fails
     */
    public static Dietitian create(int professionalId, String name, ArrayList<String> availableDays, String nutritionFocus) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        return new Dietitian(professionalId, name, availableDays, nutritionFocus);
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
        System.out.println("Dietitian " + getName() + " creates a " + nutritionFocus + " meal plan for " + patientName);
    }

    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
