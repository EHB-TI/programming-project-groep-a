package entity;

public class Beer {
    // members
    private String name, variant, color, brewery;
    private int beerID;
    private double alcoholPercentage;

    public Beer(int beerID, String name, String variant, double alcoholPercentage, String color, String brewery) {
        this.beerID = beerID;
        this.name = name;
        this.variant = variant;
        this.color = color;
        this.alcoholPercentage = alcoholPercentage;
        this.brewery = brewery;
    }

    public Beer(String name, String variant, double alcoholPercentage, String color, String brewery) {
        this.name = name;
        this.variant = variant;
        this.color = color;
        this.alcoholPercentage = alcoholPercentage;
        this.brewery = brewery;
    }



// linken nog aan brouwerij?

    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVariant() {
        return variant;
    }

    public void setVariant(String variant) {
        this.variant = variant;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrewery() {
        return brewery;
    }

    public void setBrewery(String brewery) {
        this.brewery = brewery;
    }

    public int getBeerID() {
        return beerID;
    }

    public void setBeerID(int beerID) {
        this.beerID = beerID;
    }

    public double getAlcoholPercentage() {
        return alcoholPercentage;
    }

    public void setAlcoholPercentage(double alcoholPercentage) {
        this.alcoholPercentage = alcoholPercentage;
    }
}
