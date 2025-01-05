import java.sql.*;
import java.util.Date;
import java.util.List;

public class EmployeePayrollService {

    public static void main(String[] args) {
        try {
            // Get the singleton instance of the Payroll DB Service
            PayrollDBService payrollDBService = PayrollDBService.getInstance();

            java.util.Date startDateUtil = new java.util.Date();
            java.util.Date endDateUtil = new java.util.Date();

            // Convert java.util.Date to java.sql.Date
            java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
            java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());

            // Get employees by date range
            List<EmployeePayroll> employees = payrollDBService.getEmployeesByDateRange(startDate, endDate);

            // Display the retrieved employees
            if (employees.isEmpty()) {
                System.out.println("No employees found for the given date range.");
            } else {
                System.out.println("Employees who joined between " + startDate + " and " + endDate + ":");
                for (EmployeePayroll employee : employees) {
                    System.out.println("Name: " + employee.getName() + ", Salary: " + employee.getSalary() +
                            ", Start Date: " + employee.getStartDate());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
