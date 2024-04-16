import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectDatabase {

    private static final Logger LOGGER = Logger.getLogger(ConnectDatabase.class.getName());

    public static void saveEmployeesToDatabase(String strServer, String strDatabase, List<Employee> employees) {
        String sql = "INSERT INTO Employees (ID, FullName, BirthDay, Phone, Email, EmployeeType) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = getConnection(strServer, strDatabase);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (Employee employee : employees) {
                statement.setInt(1, employee.getID());
                statement.setString(2, employee.getFullName());
                statement.setDate(3, new java.sql.Date(employee.getBirthDay().getTime()));
                statement.setString(4, employee.getPhone());
                statement.setString(5, employee.getEmail());
                statement.setString(6, employee.getEmployeeType());
                statement.executeUpdate();
            }

            LOGGER.info("Data saved to database successfully.");

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception: ", e);
        }
    }

    public static Connection getConnection(String strServer, String strDatabase) {
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://" + strServer + ":1433;databaseName=" + strDatabase
                    + ";integratedSecurity=true;encrypt=false";
            connection = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "SQL Exception: ", e);
        } catch (ClassNotFoundException cE) {
            LOGGER.log(Level.SEVERE, "Class Not Found Exception: ", cE);
        }

        return connection;
    }

    public static void main(String[] args) {
        Connection conn = getConnection("DESKTOP-KED88T5", "QLNV");
        if (conn != null)
            LOGGER.info("Connected to SQL Server database successfully.");
        else
            LOGGER.warning("Failed to connect to SQL Server database.");
    }
}