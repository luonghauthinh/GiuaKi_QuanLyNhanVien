import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EmployeeManagement{
    private static final long serialVersionUID = 1L;
    private List<Employee> employees;
    private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO Employees (ID, FullName, BirthDay, Phone, Email, EmployeeType, ExpInYear, ProSkill, GraduationDate, GraduationRank, Education, Majors, Semester, UniversityName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_EMPLOYEES_SQL = "SELECT * FROM Employees";

    public EmployeeManagement() {
        this.employees = new ArrayList<>();
    }
    
    public void updateEmployeeFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter employee ID to update:");
        int updateID = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter full name:");
        String updateFullName = scanner.nextLine();

        System.out.println("Enter birth day (yyyy-MM-dd):");
        String updateBirthDayStr = scanner.nextLine();
        Date updateBirthDay = parseDate(updateBirthDayStr);

        System.out.println("Enter phone number:");
        String updatePhone = scanner.nextLine();

        System.out.println("Enter email:");
        String updateEmail = scanner.nextLine();

        System.out.println("Enter employee type (Experience/Fresher/Intern):");
        String updateEmployeeType = scanner.nextLine();

        // Directly call the updateEmployee method
        updateEmployee(updateID, updateFullName, updateBirthDay, updatePhone, updateEmail, updateEmployeeType);
    }
    
    public void createEmployeeFromConsole() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter full name:");
        String fullName = scanner.nextLine();

        System.out.println("Enter birth day (yyyy-MM-dd):");
        String birthDayStr = scanner.nextLine();
        Date birthDay = parseDate(birthDayStr);

        System.out.println("Enter phone number:");
        String phone = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter employee type (Experience/Fresher/Intern):");
        String employeeType = scanner.nextLine();

        switch (employeeType.toLowerCase()) {
            case "experience":
                System.out.println("Enter years of experience:");
                int expInYear = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter professional skill:");
                String proSkill = scanner.nextLine();
                employees.add(new Experience(fullName, birthDay, phone, email, expInYear, proSkill));
                break;
            case "fresher":
                System.out.println("Enter graduation date (yyyy-MM-dd):");
                String graduationDateStr = scanner.nextLine();
                Date graduationDate = parseDate(graduationDateStr);
                System.out.println("Enter graduation rank:");
                String graduationRank = scanner.nextLine();
                System.out.println("Enter education:");
                String education = scanner.nextLine();
                employees.add(new Fresher(fullName, birthDay, phone, email, graduationDate, graduationRank, education));
                break;
            case "intern":
                System.out.println("Enter major:");
                String majors = scanner.nextLine();
                System.out.println("Enter semester:");
                int semester = scanner.nextInt();
                scanner.nextLine(); 
                System.out.println("Enter university name:");
                String universityName = scanner.nextLine();
                employees.add(new Intern(fullName, birthDay, phone, email, majors, semester, universityName));
                break;
            default:
                System.out.println("Invalid employee type.");
        }
    }


    public void displayAllEmployees() {
        for (Employee employee : employees) {
            employee.showInfo();
            System.out.println();
        }
    }


    public void updateEmployee(int ID, String fullName, Date birthDay, String phone, String email, String employeeType) {
        for (Employee employee : employees) {
            if (employee.getID() == ID) {
                employee.setFullName(fullName);
                employee.setBirthDay(birthDay);
                employee.setPhone(phone);
                employee.setEmail(email);
                employee.setEmployeeType(employeeType);
                return;
            }
        }
        System.out.println("Employee with ID " + ID + " not found.");
    }


    public void deleteEmployee(int ID) {
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getID() == ID) {
                iterator.remove();
                System.out.println("Employee with ID " + ID + " has been deleted.");
                return;
            }
        }
        System.out.println("Employee with ID " + ID + " not found.");
    }

    public void loadFromFileConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file name to load:");
        String fileName = scanner.nextLine();
        loadFromFile(fileName);
    }

    public void saveToFile(String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            List<Employee> employeeList = new ArrayList<>();
            for (Employee employee : employees) {
                employeeList.add(employee);
            }
            oos.writeObject(employeeList);
            System.out.println("Data saved to file: " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            employees = (List<Employee>) ois.readObject();
            System.out.println("Data loaded from file: " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }
    
    public void saveToDatabase(String strServer, String strDatabase) {
        try (Connection connection = ConnectDatabase.getConnection(strServer, strDatabase);
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL)) {

            for (Employee employee : employees) {
                preparedStatement.setString(1, employee.getFullName());
                preparedStatement.setString(2, new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthDay()));
                preparedStatement.setString(3, employee.getPhone());
                preparedStatement.setString(4, employee.getEmail());
                preparedStatement.setString(5, employee.getEmployeeType());

                if (employee instanceof Experience) {
                    preparedStatement.setInt(6, ((Experience) employee).getExpInYear());
                    preparedStatement.setString(7, ((Experience) employee).getProSkill());
                    preparedStatement.setNull(8, java.sql.Types.DATE);
                    preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(10, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(12, java.sql.Types.INTEGER);
                    preparedStatement.setNull(13, java.sql.Types.VARCHAR);
                } else if (employee instanceof Fresher) {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                    preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                    preparedStatement.setString(8, new SimpleDateFormat("yyyy-MM-dd").format(((Fresher) employee).getGraduationDate()));
                    preparedStatement.setString(9, ((Fresher) employee).getGraduationRank());
                    preparedStatement.setString(10, ((Fresher) employee).getEducation());
                    preparedStatement.setNull(11, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(12, java.sql.Types.INTEGER);
                    preparedStatement.setNull(13, java.sql.Types.VARCHAR);
                } else if (employee instanceof Intern) {
                    preparedStatement.setNull(6, java.sql.Types.INTEGER);
                    preparedStatement.setNull(7, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(8, java.sql.Types.DATE);
                    preparedStatement.setNull(9, java.sql.Types.VARCHAR);
                    preparedStatement.setNull(10, java.sql.Types.VARCHAR);
                    preparedStatement.setString(11, ((Intern) employee).getMajors());
                    preparedStatement.setInt(12, ((Intern) employee).getSemester());
                    preparedStatement.setString(13, ((Intern) employee).getUniversityName());
                }

                preparedStatement.addBatch();
            }

            int[] updateCounts = preparedStatement.executeBatch();
            System.out.println("Data saved to database successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void loadFromDatabase(String strServer, String strDatabase) {
    	
        try (Connection connection = ConnectDatabase.getConnection(strServer, strDatabase);
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOYEES_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            System.out.println("Connected to database.");

            while (resultSet.next()) {
                String employeeType = resultSet.getString("EmployeeType");
                switch (employeeType) {
                    case "Experience":
                        employees.add(new Experience(
                                resultSet.getString("FullName"),
                                resultSet.getDate("BirthDay"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email"),
                                resultSet.getInt("ExpInYear"),
                                resultSet.getString("ProSkill")
                        ));
                        break;
                    case "Fresher":
                        employees.add(new Fresher(
                                resultSet.getString("FullName"),
                                resultSet.getDate("BirthDay"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email"),
                                resultSet.getDate("GraduationDate"),
                                resultSet.getString("GraduationRank"),
                                resultSet.getString("Education")
                        ));
                        break;
                    case "Intern":
                        employees.add(new Intern(
                                resultSet.getString("FullName"),
                                resultSet.getDate("BirthDay"),
                                resultSet.getString("Phone"),
                                resultSet.getString("Email"),
                                resultSet.getString("Majors"),
                                resultSet.getInt("Semester"),
                                resultSet.getString("UniversityName")
                        ));
                        break;
                }
            }

            System.out.println("Data loaded from database successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
