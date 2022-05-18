package entity;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        //some test data
        User u1 = new User("Alice", "Mommen", 41);
        User u2 = new User("Boris", "Cooremans", 58);
        User u3 = new User("Charlie", "Zappa", 8);
        Event e1 = new Event("Nationale IPA-conferentie");
        Event e2 = new Event("De 7e Zuipkermis");
        Event e3 = new Event("Alcoholvrij festival");

        LocalDate ld = LocalDate.of(2023, 6, 1);
        e1.setEventDate(ld);
        System.out.println(e1.getEventDate());
        e1.setEventDate(1981,6, 1);
        System.out.println(e1.getEventDate());

        try {
            e1.deleteVisitor(u1);
        } catch (NoVisitorsException e) {
            e.printStackTrace();
        }

    }
}
