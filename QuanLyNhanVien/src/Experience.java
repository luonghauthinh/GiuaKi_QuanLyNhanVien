
import java.util.Date;

public class Experience extends Employee {
    private static final long serialVersionUID = 1L;
    private int expInYear;
    private String proSkill;

    public Experience(String fullName, Date birthDay, String phone, String email, int expInYear, String proSkill) {
        super(fullName, birthDay, phone, email, "Experience");
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Experience in Years: " + expInYear);
        System.out.println("Professional Skill: " + proSkill);
    }

	public int getExpInYear() {
		return expInYear;
	}

	public void setExpInYear(int expInYear) {
		this.expInYear = expInYear;
	}

	public String getProSkill() {
		return proSkill;
	}

	public void setProSkill(String proSkill) {
		this.proSkill = proSkill;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Experience [expInYear=" + expInYear + ", proSkill=" + proSkill + ", ID=" + ID + ", fullName="
				+ fullName + ", birthDay=" + birthDay + ", phone=" + phone + ", email=" + email + ", employeeType="
				+ employeeType + ", getExpInYear()=" + getExpInYear() + ", getProSkill()=" + getProSkill()
				+ ", getID()=" + getID() + ", getFullName()=" + getFullName() + ", getBirthDay()=" + getBirthDay()
				+ ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getEmployeeType()="
				+ getEmployeeType() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
    
}
