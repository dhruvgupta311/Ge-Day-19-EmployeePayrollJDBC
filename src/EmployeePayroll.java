public class EmployeePayroll {
    private int payrollId;
    private int employeeId;
    private double salary;
    private double basicPay;
    private double deductions;
    private double taxablePay;
    private double incomeTax;
    private double netPay;

    public EmployeePayroll(int payrollId, int employeeId, double salary, double basicPay,
                           double deductions, double taxablePay, double incomeTax, double netPay) {
        this.payrollId = payrollId;
        this.employeeId = employeeId;
        this.salary = salary;
        this.basicPay = basicPay;
        this.deductions = deductions;
        this.taxablePay = taxablePay;
        this.incomeTax = incomeTax;
        this.netPay = netPay;
    }

    @Override
    public String toString() {
        return "EmployeePayroll{" +
                "payrollId=" + payrollId +
                ", employeeId=" + employeeId +
                ", salary=" + salary +
                ", basicPay=" + basicPay +
                ", deductions=" + deductions +
                ", taxablePay=" + taxablePay +
                ", incomeTax=" + incomeTax +
                ", netPay=" + netPay +
                '}';
    }
}
