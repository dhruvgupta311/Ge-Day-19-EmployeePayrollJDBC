public class EmployeePayroll {
    private int payrollId;
    private String name;
    private double salary;
    private double basicPay;
    private double deductions;
    private double taxablePay;
    private double incomeTax;
    private double netPay;
    private String startDate; // New field for backward compatibility

    // Constructor
    public EmployeePayroll(int payrollId, String name, double salary, double basicPay, double deductions,
                           double taxablePay, double incomeTax, double netPay, String startDate) {
        this.payrollId = payrollId;
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
    public int getPayrollId() {
        return payrollId;
    }

    public void setPayrollId(int payrollId) {
        this.payrollId = payrollId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getBasicPay() {
        return basicPay;
    }

    public void setBasicPay(double basicPay) {
        this.basicPay = basicPay;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getTaxablePay() {
        return taxablePay;
    }

    public void setTaxablePay(double taxablePay) {
        this.taxablePay = taxablePay;
    }

    public double getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(double incomeTax) {
        this.incomeTax = incomeTax;
    }

    public double getNetPay() {
        return netPay;
    }

    public void setNetPay(double netPay) {
        this.netPay = netPay;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
