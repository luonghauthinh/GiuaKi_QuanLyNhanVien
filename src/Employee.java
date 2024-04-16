import java.util.Date;

public class Employee implements IEmployee{
    private static final long serialVersionUID = 1L;
    private static int employeeCount = 0;

    protected int ID;
    protected String fullName;
    protected Date birthDay;
    protected String phone;
    protected String email;
    protected String employeeType;

    public Employee(String fullName, Date birthDay, String phone, String email, String employeeType) {
    	this.ID = ++employeeCount;
        this.fullName = fullName;
        this.birthDay = birthDay;
        this.phone = phone;
        this.email = email;
        this.employeeType = employeeType;
    }
    

    @Override
    public void showInfo() {
        System.out.println("ID: " + ID);
        System.out.println("Full Name: " + fullName);
        System.out.println("Birth Day: " + birthDay);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Employee Type: " + employeeType);
    }

    public int getID() {
        return ID;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "ID=" + ID +
                ", fullName='" + fullName + '\'' +
                ", birthDay=" + birthDay +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", employeeType='" + employeeType + '\'' +
                '}';
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }
}
