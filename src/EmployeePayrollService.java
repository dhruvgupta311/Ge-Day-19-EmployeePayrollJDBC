import java.sql.*;
import java.util.Date;

public class EmployeePayrollService {

    public static void main(String[] args) {
        EmployeePayroll employee = new EmployeePayroll("Terisa", 2500000.00, 2000000.00, 40000.00,
                1960000.00, 39200.00, 1920800.00, new Date());

        try {
            // Get the singleton instance of the Payroll DB Service
            PayrollDBService payrollDBService = PayrollDBService.getInstance();

            // Insert employee payroll into the database
            payrollDBService.insertEmployeePayroll(employee);

            // Update the salary of Terisa and sync it with the database
            payrollDBService.updateEmployeeSalary(employee, 3000000.00);

            // Verify the update by comparing EmployeePayroll object with DB
            EmployeePayroll updatedEmployee = payrollDBService.getEmployeePayrollByName(employee.getName());
            if (updatedEmployee != null && updatedEmployee.getSalary() == 3000000.00) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Error: Employee not found or salary update failed for " + employee.getName());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
