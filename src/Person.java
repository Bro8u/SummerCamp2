public class Person {
    int age;
    String name;
    String surname;

    String company;

    Person(String name, String surname, int age, String company) {
        this.age = age;
        this.name = name;
        this.surname = surname;
        this.company = company;
    }

    void doWork() {
        System.out.println("I work in " + company);
    }

}
