package entity;

public class Beer {
    // members
    private static int beerIDCounter = 0;
    private final int beerID; //mag weg, wordt op db gegenereerd (autoincrement)
    private String name;
    private  int ibu, rating; //POLAR: rating ook double of enum?
    private double alcoholPercentage;
    // wat nog toe te voegen?
    // constructors

    public Beer(String name, double alcoholPercentage) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.beerID = ++beerIDCounter;
    }

    public Beer(String name, double alcoholPercentage, int ibu, int rating) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.ibu = ibu;
        this.rating = rating;
        this.beerID = ++beerIDCounter;
    }
    // linken nog aan brouwerij?
    // getters

    public String getName() {
        return name;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public int getIbu() {
        return ibu;
    }

    public int getRating() {
        return rating;
    }
    // setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }

    public void setIbu(int ibu) {
        this.ibu = ibu;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
    // methods
}
