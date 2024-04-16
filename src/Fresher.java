
import java.util.Date;

public class Fresher extends Employee {
    private static final long serialVersionUID = 1L;
    private Date graduationDate;
    private String graduationRank;
    private String education;

    public Fresher(String fullName, Date birthDay, String phone, String email, Date graduationDate, String graduationRank, String education) {
        super(fullName, birthDay, phone, email, "Fresher");
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Graduation Date: " + graduationDate);
        System.out.println("Graduation Rank: " + graduationRank);
        System.out.println("Education: " + education);
    }

	public Date getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(Date graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getGraduationRank() {
		return graduationRank;
	}

	public void setGraduationRank(String graduationRank) {
		this.graduationRank = graduationRank;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Fresher[graduationDate=" + graduationDate + ", graduationRank=" + graduationRank
				+ ", education=" + education + ", ID=" + ID + ", fullName=" + fullName + ", birthDay=" + birthDay
				+ ", phone=" + phone + ", email=" + email + ", employeeType=" + employeeType + ", getGraduationDate()="
				+ getGraduationDate() + ", getGraduationRank()=" + getGraduationRank() + ", getEducation()="
				+ getEducation() + ", getID()=" + getID() + ", getFullName()=" + getFullName() + ", getBirthDay()="
				+ getBirthDay() + ", getPhone()=" + getPhone() + ", getEmail()=" + getEmail() + ", getEmployeeType()="
				+ getEmployeeType() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
    
}
