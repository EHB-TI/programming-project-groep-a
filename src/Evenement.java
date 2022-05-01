import java.util.Date;

public class Evenement {
    //members
    private static int eventCounter = 0;
    private final int eventID;
    private String title;
    private Date startDate;
    private Date endDate;
    //TO DO organising brewery
    //TO DO featured beer
    //TO DO region (or is this based on featured beer?)
    //TO DO visitors []

    //constructors
    public Evenement() {
        this.eventID = ++eventCounter;
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

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    //setters
    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    //methods
    //TO DO add visitor to event
        //TO DO add visitor to visitors arrayList
        //TO DO check if user already in visitors arrayList. If so, throw an exception
        //TO DO check that event's start date hasn't passed yet. If so, don't add visitors

    //TO DO delete visitor from event

    //TO DO add featured beer to event. Maximum of one.

    //TO DO delete featured beer from event

    //TO DO add an organising brewery to event. Maximum of one.

    //TO DO delete an organising brewery from event

    //TO DO print the event's details plus a list of visitors

//ending brackets (because I get confused sometimes)
}
