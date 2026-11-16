/**
 * Represents a patient with basic contact details.
 * Objects can only be created via the static factory method.
 * Invalid inputs will return null and no object is created.
 * @author Meng Lv
 */
public class Patient {
    private String fullName;
    private String mobilePhone;

    /**
     * Private constructor: internal use only.
     * Callers must use the create() factory method to obtain instances.
     * @param fullName validated patient name
     * @param mobilePhone validated 10-digit phone number
     */
    private Patient(String fullName, String mobilePhone) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
    }

    /**
     * Static factory method to create a valid Patient object.
     * Returns null if inputs are invalid.
     * @param fullName patient full name, must not be blank
     * @param mobilePhone 10-digit numeric phone number
     * @return valid Patient object, or null if validation fails
     */
    public static Patient create(String fullName, String mobilePhone) {
        if (fullName == null || fullName.isBlank()) {
            return null;
        }
        if (!isValidMobile(mobilePhone)) {
            return null;
        }
        return new Patient(fullName, mobilePhone);
    }

    /**
     * Checks if a phone number is a valid 10-digit numeric string.
     * @param phone phone number to check
     * @return true if valid
     */
    private static boolean isValidMobile(String phone) {
        if (phone == null || phone.length() != 10) {
            return false;
        }
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setFullName(String fullName) {
        if (fullName != null && !fullName.isBlank()) {
            this.fullName = fullName;
        }
    }

    public void setMobilePhone(String mobilePhone) {
        if (isValidMobile(mobilePhone)) {
            this.mobilePhone = mobilePhone;
        }
    }

    @Override
    public String toString() {
        return "Patient: " + fullName + " | Phone: " + mobilePhone;
    }
}
