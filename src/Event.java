import java.util.Date;
import java.util.TreeSet;

public class Event {
    //members
    private static int eventCounter = 0;
    private final int eventID;
    private String title;
    private Date eventDate = new Date(); //TO DO param is milliseconds, need to find a better way
    //TO DO organising brewery
    //TO DO featured beer
    private Region region;
    private TreeSet<User> visitors = new TreeSet<User>();

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

    public void setVisitors(TreeSet<User> visitors) {
        this.visitors = visitors;
    }

    public void setRegion(Region region) {
        this.region = region;
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
    //TO DO add visitor to event
    public void addVisitor(User u) {
        if (this.visitors == null)
            this.visitors = new TreeSet<User>();
        this.visitors.add(u); //TO DO add two users then it only adds one because they are the same :( How to fix this?
    }

    //delete visitor from event
    public void deleteVisitor(User u) {
        if (!(this.visitors == null))
            this.visitors.remove(u);
    }

    //TO DO add featured beer to event. Maximum of one.

    //TO DO delete featured beer from event

    //TO DO add an organising brewery to event. Maximum of one.

    //TO DO delete an organising brewery from event

//ending brackets (because I get confused sometimes)
}
