import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
        User u1 = new User("R", "C", 41);
        User u2 = new User("C", "V", 44);
        User u3 = new User("J", "V", 8);
        Event e = new Event();

        e.addVisitor(u1);
        e.addVisitor(u2);
        e.addVisitor(u3);

        System.out.println(e.getVisitors());
    }
}
