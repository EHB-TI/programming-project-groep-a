import java.util.Date;

//implements Comparable because Event class wants to add users to a visitors TreeSet
public class User implements Comparable<User>{
    //members
    private static int userCounter = 0;
    private final int userID;
    private String name, surname;
    private int age;
    // Automatisch huidige datum als waarde toevoegen?
    private final Date joiningDate;
    private int favoriteBeer;

    // Lijst gedronken bieren
    // Lijst bezochte plaatsen/streken
    // Nog dingen?

    //constructors
    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.userID = userCounter; // Klopt dit?
        joiningDate = new Date();
        userCounter++;
    }

    public User(String name, String surname, int age, int favoriteBeer) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.favoriteBeer = favoriteBeer;
        this.userID = userCounter; // Klopt dit?
        joiningDate = new Date();
        userCounter++;
    }

    // Getters
    public int getUserID() {
        return userID;
    }

    public static int getUserCounter() {
        return userCounter;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getAge() {
        return age;
    }

    public int getFavoriteBeer() {
        return favoriteBeer;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void setFavoriteBeer(int favoriteBeer) {
        this.favoriteBeer = favoriteBeer;
    }

    //overrides
    @Override //because User implements Comparable
    public int compareTo(User o) {
        if (name == null) {
            return o.name == null ? 0 : 1;
        }
        else return this.name.compareTo(o.name);
    }

    // Methods
    // TO DO add Beer to Beer list
    // TO DO add locations to location list
    // TO DO delete user, how to deal with user counter and user ID's?
}
