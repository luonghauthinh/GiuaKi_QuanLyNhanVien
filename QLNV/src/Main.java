import java.util.Scanner;

public class Main {
	
    public static void main(String[] args) {
        EmployeeManagement management = new EmployeeManagement();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        

        while (!exit) {
        	
            System.out.println("\nOption:");
            System.out.println("1. Create Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Save Employees to File");
            System.out.println("6. Load Employees from File");
            System.out.println("7. Save Employees to Database");
            System.out.println("8. Load Employees from Database");
            System.out.println("9. Exit");
            System.out.println("Choose an option:");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    management.createEmployeeFromConsole();
                    break;
                case 2:
                    management.displayAllEmployees();
                    break;
                case 3:
                    management.updateEmployeeFromConsole();
                    break;
                case 4:
                    System.out.println("Enter employee ID to delete:");
                    int deleteID = scanner.nextInt();
                    scanner.nextLine();
                    management.deleteEmployee(deleteID);
                    break;
                case 5:
                    System.out.println("Enter file name to save:");
                    String fileName = scanner.nextLine();
                    management.saveToFile(fileName);
                    break;

                case 6:
                    management.loadFromFileConsole();
                    break;
                case 7:
                    System.out.println("Enter database server:");
                    String server = scanner.nextLine();
                    System.out.println("Enter database name:");
                    String database = scanner.nextLine();
                    management.saveToDatabase(server, database);
                    break;
                case 8:
                    System.out.println("Enter database server:");
                    server = scanner.nextLine();
                    System.out.println("Enter database name:");
                    database = scanner.nextLine();
                    management.loadFromDatabase(server, database);
                    break;
                case 9:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        System.out.println("Bye!");
        scanner.close();
    }
}
