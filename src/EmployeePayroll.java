import java.util.Date;

public class EmployeePayroll {

    private String name;
    private double salary;
    private double basicPay;
    private double deductions;
    private double taxablePay;
    private double incomeTax;
    private double netPay;
    private Date startDate;

    public EmployeePayroll(String name, double salary, double basicPay, double deductions,
                           double taxablePay, double incomeTax, double netPay, Date startDate) {
        this.name = name;
        this.salary = salary;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
        this.startDate = startDate;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getTaxablePay() {
        return taxablePay;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public double getNetPay() {
        return netPay;
    }

    public Date getStartDate() {
        return startDate;
    }
}
