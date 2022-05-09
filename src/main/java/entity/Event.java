package entity;

import java.util.Date;
import java.util.TreeSet;

public class Event {
    //members
    private static int eventCounter = 0;
    private final int eventID;
    private String title;
    private Date eventDate = null; //TO DO param is milliseconds, need to find a better way to input date, then convert to Date object
    //TO DO private Brewery organisingBrewery;
    private Beer featuredBeer;
    private Region region = null;
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

    public Date getEventDate() {
        return eventDate;
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

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    //TO DO add an organising brewery to event

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
                "Date: " + getEventDate() + "\n";
        //TO DO Print "entity.Region: " + getRegion().getName() + "\n";
        //TO DO print visitors
    }

    //methods
    //add visitor to event
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

    //delete visitor from event
    public void deleteVisitor(User u) throws NoVisitorsException {
        //check if list is empty
        if (this.visitors == null)
            throw new NoVisitorsException();
        else { //loop over the list and compare
            for (User user : this.visitors) { //QUESTION - can I do this with a TreeSet? This doesn't feel right...
                if (user.compareTo(u) == 0) {
                    //remove the visitor
                    this.visitors.remove(u);
                    System.out.println("Visitor deleted");
                }
            }
        }
    }
//ending brackets (because I get confused sometimes)
}
