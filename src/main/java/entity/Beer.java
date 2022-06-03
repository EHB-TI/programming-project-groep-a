package entity;

public class Beer {
    // members
    private String name, variant, color;
    private double alcoholPercentage;

    public Beer(String name, String variant, String color, double alcoholPercentage) {
        this.name = name;
        this.variant = variant;
        this.color = color;
        this.alcoholPercentage = alcoholPercentage;
    }

    // linken nog aan brouwerij?

    // getters and setters
    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public String getVariant() {return variant;}

    public void setVariant(String variant) {this.variant = variant;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color;}

    public double getAlcoholPercentage() {return alcoholPercentage;}

    public void setAlcoholPercentage(double alcoholPercentage) {this.alcoholPercentage = alcoholPercentage;}
}
