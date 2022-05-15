package entity;

public class Region {
    //members
    private static int regionCounter = 0;
    private final int regionID;
    private String name, country;
    // Nog dingen? Misschien een enum met enkele regions?

    //constructors
    public Region(String name, String country) {
        this.name = name;
        this.country = country;
        this.regionID = regionCounter++;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getRegionID() {
        return regionID;
    }

    public static int getRegionCounter() {
        return regionCounter;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    // Methods
    // TO DO delete regions, how to deal with region counter and region ID's? >> Ik zou de counters gewoon laten doortellen, de ID's worden gedelete samen met het object denk ik

}
