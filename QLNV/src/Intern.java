
import java.io.Serializable;
import java.util.Date;
	
public class Intern extends Employee implements Serializable{
    private static final long serialVersionUID = 1L;
    private String majors;
    private int semester;
    private String universityName;
    
    public Intern() {
        super("", new Date(), "", "", "Intern");
        this.majors = "";
        this.semester = 0;
        this.universityName = "";
    }

    public Intern(String fullName, Date birthDay, String phone, String email, String majors, int semester, String universityName) {
        super(fullName, birthDay, phone, email, "Intern");
        this.majors = majors;
        this.semester = semester;
        this.universityName = universityName;
    }

    @Override
    public void showInfo() {
        super.showInfo();
        System.out.println("Majors: " + majors);
        System.out.println("Semester: " + semester);
        System.out.println("University Name: " + universityName);
    }

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Intern[majors=" + majors + ", semester=" + semester + ", universityName=" + universityName
				+ ", ID=" + ID + ", fullName=" + fullName + ", birthDay=" + birthDay + ", phone=" + phone + ", email="
				+ email + ", employeeType=" + employeeType + ", getMajors()=" + getMajors() + ", getSemester()="
				+ getSemester() + ", getUniversityName()=" + getUniversityName() + ", getID()=" + getID()
				+ ", getFullName()=" + getFullName() + ", getBirthDay()=" + getBirthDay() + ", getPhone()=" + getPhone()
				+ ", getEmail()=" + getEmail() + ", getEmployeeType()=" + getEmployeeType() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}
    
}
