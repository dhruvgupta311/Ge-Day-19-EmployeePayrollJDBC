import java.sql.*;
import java.util.*;

public class EmployeePayrollService {

    public static void main(String[] args) {
        try {
            // Get the singleton instance of PayrollDBService
            PayrollDBService payrollDBService = PayrollDBService.getInstance();

            // Get the aggregate analysis for male and female employees
            AggregateResult maleResults = payrollDBService.getGenderBasedAggregates('M');
            AggregateResult femaleResults = payrollDBService.getGenderBasedAggregates('F');

            // Display the results for male employees
            System.out.println("Male Employee Salary Analysis:");
            displayAggregateResults(maleResults);

            // Display the results for female employees
            System.out.println("Female Employee Salary Analysis:");
            displayAggregateResults(femaleResults);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to display the aggregate results
    private static void displayAggregateResults(AggregateResult result) {
        System.out.println("Sum of Salaries: " + result.getSumSalary());
        System.out.println("Average Salary: " + result.getAverageSalary());
        System.out.println("Minimum Salary: " + result.getMinSalary());
        System.out.println("Maximum Salary: " + result.getMaxSalary());
        System.out.println("Number of Employees: " + result.getEmployeeCount());
        System.out.println();
    }
}
