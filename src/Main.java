public class Main {

    public static void main(String[] args) {
        System.out.println("Hello world!");
        Employee sam = new Employee("Sam", "Brunov", 20, "Yandex", 25);
//        Boss bob = new Boss("Boris", "Yakunin", 15, "Microsoft");
//        bob.fireEmployee(sam);
        Person p = new Boss("Boris", "Yakunin", 15, "Microsoft");
        System.out.println(p.name);
    }
}