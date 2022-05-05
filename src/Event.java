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
    private Region region;
    private TreeSet<User> visitors = null;

    //constructors
    public Event() {
        this.eventID = ++eventCounter;
    }

    public Event(String title, Region region) {
        this.eventID = ++eventCounter;

        this.title = title;
        this.region = region;
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
        return  "Title: " + getTitle() + "\n" +
                "Date: " + getEventDate() + "\n" +
                "Region: " + getRegion().getName() + "\n";
        //TO DO print visitors
    }

    //methods
    //STUCK add visitor to event
    public void addVisitor(User u) {
        //check if TreeSet exists
        if (this.visitors == null)
            this.visitors = new TreeSet<User>();

        //compare u to every element in the TreeSet

        //add u to TreeSet
        this.visitors.add(u);
    }

    //delete visitor from event
    public void deleteVisitor(User u) {
        if (!(this.visitors == null))
            this.visitors.remove(u);
    }

//ending brackets (because I get confused sometimes)
}
