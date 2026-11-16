/**
 * Represents a patient with basic contact details.
 * @author Meng Lv
 */
public class Patient {
    private String fullName;
    private String mobilePhone;

    /**
     * Constructs a patient record.
     * @param fullName patient full name
     * @param mobilePhone contact phone number
     */
    public Patient(String fullName, String mobilePhone) {
        this.fullName = fullName;
        this.mobilePhone = mobilePhone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String toString() {
        return "Patient: " + fullName + " | Phone: " + mobilePhone;
    }
}
