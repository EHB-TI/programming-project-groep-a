package entity;

import java.util.ArrayList;

public class Brewery {
        private String Name_Brewery; //misschien beter variabelen lowercase maken?

        private final int IDBrewery;

        private String Region;

        private String Adres;

        private String Brands;

        //optie? private ArrayList<Beer> beerlist = new ArrayList<Beer>();

        //construct

        public Brewery(String name_Brewery, int IDBrewery, String region, String Adres, String brands) {
            this.Name_Brewery = name_Brewery;
            this.IDBrewery = IDBrewery; // misschien beter automatisch laten berekenen met een increment operator en static ID-variabele?
            this.Region = region;
            this.Adres = Adres;
            this.Brands = brands;
        }
        //getters

        public String getName_Brewery() {
            return Name_Brewery;
        }

        public int getIDBrewery() {
            return IDBrewery;
        }

        public String getRegion() {
            return Region;
        }

        public String getAdres() {
            return Adres;
        }

        public String getBrands() {
            return Brands;
        }

        //setters


        public void setName_Brewery(String name_Brewery) {
            Name_Brewery = name_Brewery;
        }

        public void setRegion(String region) {
            Region = region;
        }

        public void setAdres(String adres) {
            Adres = adres;
        }

        public void setBrands(String brands) {
            Brands = brands;
        }
}

