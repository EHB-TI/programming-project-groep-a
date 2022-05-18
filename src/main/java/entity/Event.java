package entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.TreeSet;

public class Event {
    //members
    private static int eventCounter = 0; //mag weg, wordt op db gegenereerd (autoincrement)
    private final int eventID; //mag weg, wordt op db gegenereerd (autoincrement)
    private String title;
    private LocalDate eventDate = null;
    private Brewery organisingBrewery;
    private Beer featuredBeer;
    private Region region;
    private TreeSet<User> visitors = null;

    //constructors
    public Event() {
        this.eventID = ++eventCounter;
    }

    public Event(String title) {
        this.eventID = ++eventCounter;
        this.title = title;
    }

    //getters
    public static int getEventCounter() {
        return eventCounter;
    }

    public int getEventID() {
        return eventID;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public Brewery getOrganisingBrewery() {
        return organisingBrewery;
    }

    public TreeSet<User> getVisitors() {
        return visitors;
    }

    public Beer getFeaturedBeer() {
        return featuredBeer;
    }

    public Region getRegion() {
        return region;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setEventDate(LocalDate eventDate) {
      this.eventDate = eventDate;
    }

    public void setEventDate(int year, int month, int day) {
        this.eventDate = LocalDate.of(year, month, day);
    }

    public void setOrganisingBrewery(Brewery organisingBrewery) {
        this.organisingBrewery = organisingBrewery;
    }

    public void setFeaturedBeer(Beer featuredBeer) {
        this.featuredBeer = featuredBeer;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setVisitors(TreeSet<User> visitors) {
        this.visitors = visitors;
    }

    //overrides
    //TO DO print the event's details plus a list of visitors
    @Override
    public String toString() {
        return "Title: " + getTitle() + "\n" +
                "Date: " + getEventDate() + "\n" +
                "Region: " + getRegion() + "\n";
        //TO DO print visitors
    }

    //methods
    //add visitor to event
    //TO DO rechtstreeks op EventDAO werken
    public void addVisitor(User u) throws UserAlreadyAddedException {
        //check if TreeSet exists
        if (this.visitors == null)
            this.visitors = new TreeSet<User>();
        //compare u to every element in the TreeSet
        for (User user : this.visitors) {
            if (user.compareTo(u) == 0)
                throw new UserAlreadyAddedException();
        }
        //add u to TreeSet
        this.visitors.add(u);
    }

    //TO DO - STUCK? only deletes one? - delete visitor from event
    //TO DO rechtstreeks op EventDAO werken
    public void deleteVisitor(User u) throws NoVisitorsException {
        //check if list is empty
        if (this.visitors == null)
            throw new NoVisitorsException();
        else {
            //loop over the list with an iterator
            Iterator<User> it = this.visitors.iterator();
            while(it.hasNext()){
                User user = it.next();
                //compare
                if (user.compareTo(u) == 0) {
                    //remove the visitor
                    it.remove();
                    System.out.println("Visitor deleted");
                }
            }
        }

    }

//ending brackets (because I get confused sometimes)
}
