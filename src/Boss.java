public class Boss extends Person {

    Boss(String name, String surname, int age, String company) {
        super(name, surname, age, company);
    }

    void doWork() {
        super.doWork();
        System.out.println("I am boss");
    }

    void increaseEmployeeSalary(Employee employee, int increase) {
        employee.salary += increase;
    }

    void fireEmployee(Employee employee) {
        System.out.println("Boss fired employee " + employee);
    }
}
