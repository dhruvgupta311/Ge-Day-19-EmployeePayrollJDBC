public class AggregateResult {

    private double sumSalary;
    private double averageSalary;
    private double minSalary;
    private double maxSalary;
    private int employeeCount;

    // Constructor
    public AggregateResult(double sumSalary, double averageSalary, double minSalary, double maxSalary, int employeeCount) {
        this.sumSalary = sumSalary;
        this.averageSalary = averageSalary;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.employeeCount = employeeCount;
    }

    // Getter methods
    public double getSumSalary() {
        return sumSalary;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public double getMinSalary() {
        return minSalary;
    }

    public double getMaxSalary() {
        return maxSalary;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }
}
