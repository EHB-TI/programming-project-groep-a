import java.util.Date;

public class Evenement {
    //members
    private static int eventCounter = 0;
    private final int eventID;
    private String title;
    private Date startDate;
    private Date endDate;
    //TO DO brouwerij
    //TO DO bier
    //TO DO streek
    //TO DO bezoekers []

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
}
