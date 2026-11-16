import java.util.ArrayList;

/**
 * Dietitian health professional type.
 * Provides nutrition advice and personalised meal plans.
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
     * Returns null if core fields are invalid or no working days are provided.
     * @param professionalId positive numeric ID
     * @param name non-blank full name
     * @param availableDays list of working weekdays
     * @param nutritionFocus area of nutrition expertise
     * @return valid Dietitian object, or null if validation fails
     */
    public static Dietitian create(int professionalId, String name, ArrayList<String> availableDays, String nutritionFocus) {
        if (professionalId <= 0) return null;
        if (name == null || name.isBlank()) return null;
        if (availableDays == null) return null;
        if (nutritionFocus == null || nutritionFocus.isBlank()) return null;

        ArrayList<String> cleanedDays = HealthProfessional.cleanWeekdayList(availableDays);
        if (cleanedDays.isEmpty()) return null;

        return new Dietitian(professionalId, name, availableDays, nutritionFocus);
    }

    /**
     * Returns the role name of this professional.
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
     * Blank or null values are ignored.
     * @param nutritionFocus new expertise area
     */
    public void setNutritionFocus(String nutritionFocus) {
        if (nutritionFocus != null && !nutritionFocus.isBlank()) {
            this.nutritionFocus = nutritionFocus;
        }
    }

    /**
     * Simulates creating a personalised meal plan.
     * @param patientName name of the patient receiving the plan
     */
    public void createMealPlan(String patientName) {
        System.out.println("Dietitian " + getName() + " creates a " + nutritionFocus + " meal plan for " + patientName);
    }

    /**
     * Returns a formatted string with full dietitian details.
     * @return formatted professional information
     */
    @Override
    public String toString() {
        return super.toString() + " | Nutrition focus: " + nutritionFocus;
    }
}
