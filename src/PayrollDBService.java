import java.sql.*;

public class PayrollDBService {

    private static PayrollDBService payrollDBService;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = System.getenv("MY_APP_PASSWORD");

    private PayrollDBService() {}

    // Singleton method to get the instance of PayrollDBService
    public static PayrollDBService getInstance() {
        if (payrollDBService == null) {
            payrollDBService = new PayrollDBService();
        }
        return payrollDBService;
    }

    // Method to get aggregate salary data for male or female employees
    public AggregateResult getGenderBasedAggregates(char gender) throws SQLException {
        AggregateResult result = null;
        String query = "SELECT SUM(salary) AS sum_salary, AVG(salary) AS avg_salary, " +
                "MIN(salary) AS min_salary, MAX(salary) AS max_salary, COUNT(*) AS employee_count " +
                "FROM employee_payroll19 WHERE gender = ? GROUP BY gender";

        try (Connection con = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, String.valueOf(gender));

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double sumSalary = rs.getDouble("sum_salary");
                double avgSalary = rs.getDouble("avg_salary");
                double minSalary = rs.getDouble("min_salary");
                double maxSalary = rs.getDouble("max_salary");
                int employeeCount = rs.getInt("employee_count");

                result = new AggregateResult(sumSalary, avgSalary, minSalary, maxSalary, employeeCount);
            }
        }

        if (result == null) {
            throw new SQLException("No data found for gender: " + gender);
        }

        return result;
    }
}
