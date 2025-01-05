import java.sql.*;
import java.util.Date;

public class EmployeePayrollService {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = System.getenv("MY_APP_PASSWORD"); // Get the password from the environment variable

    public static void main(String[] args) {
        if (PASSWORD == null) {
            System.out.println("Error: MY_APP_PASSWORD environment variable is not set.");
            return;
        }

        EmployeePayroll employee = new EmployeePayroll("Terisa", 2500000.00, 2000000.00, 40000.00,
                1960000.00, 39200.00, 1920800.00, new Date());

        try (Connection con = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD)) {
            // Verify if the employee exists before updating
            EmployeePayroll existingEmployee = getEmployeePayrollByName(con, employee.getName());
            if (existingEmployee != null) {
                // Employee exists, proceed to update salary
                updateEmployeeSalary(con, employee, 3000000.00); // Update salary to 3,000,000
                System.out.println("Employee payroll data updated successfully!");

                // Verify the update by comparing EmployeePayroll object with DB
                EmployeePayroll updatedEmployee = getEmployeePayrollByName(con, employee.getName());
                if (updatedEmployee != null && updatedEmployee.getSalary() == 3000000.00) {
                    System.out.println("Salary updated successfully!");
                } else {
                    System.out.println("Error: Salary update failed for " + employee.getName());
                }
            } else {
                System.out.println("Error: Employee " + employee.getName() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Insert employee payroll into the database (just for reference, won't be called in this case)
    public static void insertEmployeePayroll(Connection con, EmployeePayroll employee) throws SQLException {
        String insertQuery = "INSERT INTO employee_payroll19 (name, salary, basic_pay, deductions, taxable_pay, income_tax, net_pay, start_date) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setString(1, employee.getName());
            ps.setDouble(2, employee.getSalary());
            ps.setDouble(3, employee.getBasicPay());
            ps.setDouble(4, employee.getDeductions());
            ps.setDouble(5, employee.getTaxablePay());
            ps.setDouble(6, employee.getIncomeTax());
            ps.setDouble(7, employee.getNetPay());
            ps.setDate(8, new java.sql.Date(employee.getStartDate().getTime()));
            ps.executeUpdate();
        }
    }

    // Update the salary of an employee
    public static void updateEmployeeSalary(Connection con, EmployeePayroll employee, double newSalary) throws SQLException {
        String updateQuery = "UPDATE employee_payroll19 SET salary = ?, basic_pay = ? WHERE name = ?";
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1, newSalary);
            ps.setDouble(2, newSalary - 50000.00);  // Assuming basic pay is 50,000 less than salary
            ps.setString(3, employee.getName());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Successfully updated the salary for " + employee.getName());
            } else {
                System.out.println("Error: No records updated, employee may not exist.");
            }
        }
    }

    // Get Employee Payroll by Name
    public static EmployeePayroll getEmployeePayrollByName(Connection con, String name) throws SQLException {
        String selectQuery = "SELECT * FROM employee_payroll19 WHERE name = ?";
        try (PreparedStatement ps = con.prepareStatement(selectQuery)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new EmployeePayroll(
                        rs.getString("name"),
                        rs.getDouble("salary"),
                        rs.getDouble("basic_pay"),
                        rs.getDouble("deductions"),
                        rs.getDouble("taxable_pay"),
                        rs.getDouble("income_tax"),
                        rs.getDouble("net_pay"),
                        rs.getDate("start_date")
                );
            }
        }
        return null;
    }
}
