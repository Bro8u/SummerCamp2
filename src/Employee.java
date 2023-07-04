public class Employee extends Person{
    int salary;

    Employee(String name, String surname, int age, String company, int salary) {
        super(name, surname, age, company);
    }

    void doWork() {
        super.doWork();
        System.out.println("I am employee");
    }

    public int getSalary() {
        return salary;
    }

    public double getSalaryAfterTax() {
        return salary - salary * 0.13;
    }

}
