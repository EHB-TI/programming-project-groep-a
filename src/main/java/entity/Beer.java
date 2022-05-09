package entity;

public class Beer {
    // members
    private static int beerIDCounter = 0;
    private final int beerID;
    private String name;
    private  int alcoholPercentage,ibu, rating;
    // wat nog toe te voegen?
    // constructors

    public Beer(String name, int alcoholPercentage) {
        this.name = name;
        this.alcoholPercentage = alcoholPercentage;
        this.beerID = ++beerIDCounter;
    }

    public Beer(String name, int alcoholPercentage, int ibu, int rating) {
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

    public int getAlcoholPercentage() {
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

    public void setAlcoholPercentage(int alcoholPercentage) {
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
