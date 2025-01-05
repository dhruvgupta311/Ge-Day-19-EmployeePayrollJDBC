import java.sql.*;
import java.util.Date;

public class PayrollDBService {

    private static PayrollDBService instance;
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/payroll_service?useSSL=false";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = System.getenv("MY_APP_PASSWORD");
    private static Connection con;

    // PreparedStatement cached at the class level for reuse
    private PreparedStatement selectEmployeeStmt;
    private PreparedStatement insertEmployeeStmt;
    private PreparedStatement updateEmployeeStmt;

    // Private constructor for Singleton
    private PayrollDBService() throws SQLException {
        if (PASSWORD == null) {
            throw new SQLException("Error: MY_APP_PASSWORD environment variable is not set.");
        }

        con = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        // Prepare the SQL statements and cache them
        selectEmployeeStmt = con.prepareStatement("SELECT * FROM employee_payroll19 WHERE name = ?");
        insertEmployeeStmt = con.prepareStatement("INSERT INTO employee_payroll19 (name, salary, basic_pay, deductions, taxable_pay, income_tax, net_pay, start_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        updateEmployeeStmt = con.prepareStatement("UPDATE employee_payroll19 SET salary = ?, basic_pay = ? WHERE name = ?");
    }

    // Singleton pattern to get the instance
    public static PayrollDBService getInstance() throws SQLException {
        if (instance == null) {
            instance = new PayrollDBService();
        }
        return instance;
    }

    // Insert employee payroll into the database
    public void insertEmployeePayroll(EmployeePayroll employee) throws SQLException {
        insertEmployeeStmt.setString(1, employee.getName());
        insertEmployeeStmt.setDouble(2, employee.getSalary());
        insertEmployeeStmt.setDouble(3, employee.getBasicPay());
        insertEmployeeStmt.setDouble(4, employee.getDeductions());
        insertEmployeeStmt.setDouble(5, employee.getTaxablePay());
        insertEmployeeStmt.setDouble(6, employee.getIncomeTax());
        insertEmployeeStmt.setDouble(7, employee.getNetPay());
        insertEmployeeStmt.setDate(8, new java.sql.Date(employee.getStartDate().getTime()));
        insertEmployeeStmt.executeUpdate();
    }

    // Update the salary of an employee
    public void updateEmployeeSalary(EmployeePayroll employee, double newSalary) throws SQLException {
        updateEmployeeStmt.setDouble(1, newSalary);
        updateEmployeeStmt.setDouble(2, newSalary - 50000.00);  // Assuming basic pay is 50,000 less than salary
        updateEmployeeStmt.setString(3, employee.getName());
        updateEmployeeStmt.executeUpdate();
    }

    // Get Employee Payroll by Name and populate EmployeePayroll object
    public EmployeePayroll getEmployeePayrollByName(String name) throws SQLException {
        selectEmployeeStmt.setString(1, name);
        ResultSet rs = selectEmployeeStmt.executeQuery();
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
        return null;
    }
}
