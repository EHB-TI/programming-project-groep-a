package entity;

import java.util.List;

public class Brewery {
    //members
    private String nameBrewery;
    private Region regionBrewery;
    private String addressBrewery;
    private List<Beer> brandsBrewery = null;

    //constructors

    //getters and setters
    public String getNameBrewery() {
        return nameBrewery;
    }

    public void setNameBrewery(String nameBrewery) {
        this.nameBrewery = nameBrewery;
    }

    public Region getRegionBrewery() {
        return regionBrewery;
    }

    public void setRegionBrewery(Region regionBrewery) {
        this.regionBrewery = regionBrewery;
    }

    public String getAddressBrewery() {
        return addressBrewery;
    }

    public void setAddressBrewery(String addressBrewery) {
        this.addressBrewery = addressBrewery;
    }

    public List<Beer> getBrandsBrewery() {
        return brandsBrewery;
    }

    public void setBrandsBrewery(List<Beer> brandsBrewery) {
        this.brandsBrewery = brandsBrewery;
    }

    //overrides

    //methods
}


