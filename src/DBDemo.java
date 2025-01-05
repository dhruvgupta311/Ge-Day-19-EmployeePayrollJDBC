import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

public class DBDemo {
    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
        String userName = "root";
        String password = System.getenv("MY_APP_PASSWORD");

        if (password == null || password.isEmpty()) {
            System.err.println("Environment variable MY_APP_PASSWORD is not set or empty. Exiting...");
            return;
        }

        Connection con;

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        // Call the listDrivers() function to display all available drivers
        listDrivers();

        try {
            // Establish database connection
            System.out.println("Connecting to database: " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successful! " + con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to list all available drivers
    private static void listDrivers() {
        System.out.println("Listing all registered JDBC drivers:");
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println("Registered Driver: " + driverClass.getClass().getName());
        }
    }
}
