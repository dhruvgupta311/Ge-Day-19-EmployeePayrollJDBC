import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollService {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = System.getenv("MY_APP_PASSWORD");

    public static void main(String[] args) {
        if (PASSWORD == null || PASSWORD.isEmpty()) {
            System.err.println("Environment variable MY_APP_PASSWORD is not set or empty. Exiting...");
            return;
        }

        try {
            List<EmployeePayroll> payrollList = retrievePayrollData();
            payrollList.forEach(System.out::println);
        } catch (PayrollException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static List<EmployeePayroll> retrievePayrollData() throws PayrollException {
        List<EmployeePayroll> payrollList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     "SELECT payroll_id, employee_id, salary, basic_pay, deductions, taxable_pay, income_tax, net_pay FROM payroll")) {

            while (resultSet.next()) {
                int payrollId = resultSet.getInt("payroll_id");
                int employeeId = resultSet.getInt("employee_id");
                double salary = resultSet.getDouble("salary");
                double basicPay = resultSet.getDouble("basic_pay");
                double deductions = resultSet.getDouble("deductions");
                double taxablePay = resultSet.getDouble("taxable_pay");
                double incomeTax = resultSet.getDouble("income_tax");
                double netPay = resultSet.getDouble("net_pay");

                EmployeePayroll payroll = new EmployeePayroll(
                        payrollId, employeeId, salary, basicPay, deductions, taxablePay, incomeTax, netPay);
                payrollList.add(payroll);
            }

        } catch (SQLException e) {
            throw new PayrollException("Failed to retrieve payroll data: " + e.getMessage());
        }

        return payrollList;
    }
}
