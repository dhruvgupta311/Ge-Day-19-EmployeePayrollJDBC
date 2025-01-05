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
            List<EmployeePayroll> payrollList = getEmployeePayrollData();
            for (EmployeePayroll payroll : payrollList) {
                System.out.println("Payroll ID: " + payroll.getPayrollId());
                System.out.println("Name: " + payroll.getName());
                System.out.println("Salary: " + payroll.getSalary());
                System.out.println("Basic Pay: " + payroll.getBasicPay());
                System.out.println("Deductions: " + payroll.getDeductions());
                System.out.println("Taxable Pay: " + payroll.getTaxablePay());
                System.out.println("Income Tax: " + payroll.getIncomeTax());
                System.out.println("Net Pay: " + payroll.getNetPay());
                System.out.println("Start Date: " + payroll.getStartDate());
                System.out.println("==========");
            }
        } catch (SQLException | CustomException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Method to retrieve employee payroll data
    public static List<EmployeePayroll> getEmployeePayrollData() throws SQLException, CustomException {
        List<EmployeePayroll> payrollList = new ArrayList<>();
        String query = "SELECT payroll_id, name, salary, basic_pay, deductions, taxable_pay, income_tax, net_pay, start_date FROM employee_payroll19";

        try (Connection con = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
             PreparedStatement stmt = con.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int payrollId = rs.getInt("payroll_id");
                String name = rs.getString("name");
                double salary = rs.getDouble("salary");
                double basicPay = rs.getDouble("basic_pay");
                double deductions = rs.getDouble("deductions");
                double taxablePay = rs.getDouble("taxable_pay");
                double incomeTax = rs.getDouble("income_tax");
                double netPay = rs.getDouble("net_pay");
                String startDate = rs.getString("start_date");

                // Populate EmployeePayroll object
                EmployeePayroll payroll = new EmployeePayroll(payrollId, name, salary, basicPay, deductions, taxablePay, incomeTax, netPay, startDate);
                payrollList.add(payroll);
            }

        } catch (SQLException e) {
            throw new CustomException("Error retrieving payroll data: " + e.getMessage());
        }

        return payrollList;
    }
}
