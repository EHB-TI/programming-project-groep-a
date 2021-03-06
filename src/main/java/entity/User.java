package entity;

import java.time.LocalDate;

//implements Comparable because entity. Event class wants to add users to a visitors TreeSet
public class User implements Comparable<User>{
    //members
    private String name, surname, favoriteBeer, profession, residence, email;
    private int userID;
    private LocalDate DOB, joiningDate;
    private String gender;

    public User(String name, String surname, LocalDate DOB, String gender, String favoriteBeer, String profession,  String residence, String email) {
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
        this.gender = gender;
        this.profession = profession;
        this.favoriteBeer = favoriteBeer;
        this.residence = residence;
        this.email = email;
    }
    // This constructor is for reading a user from the database as it
    // contains the automatically generated (upon user addition) joiningDate as well as ID
    public User(int userID, String name, String surname, LocalDate DOB, String gender,  String favoriteBeer, String profession,  String residence, String email, LocalDate joiningDate) {
        this.userID = userID;
        this.name = name;
        this.surname = surname;
        this.DOB = DOB;
        this.gender = gender;
        this.profession = profession;
        this.favoriteBeer = favoriteBeer;
        this.residence = residence;
        this.email = email;
        this.joiningDate = joiningDate;
    }

    // Getters
    public int getUserID() {return userID;}

    public String getName() {return name;}

    public String getSurname() {return surname;}

    public String getFavoriteBeer() {return favoriteBeer;}

    public String getProfession() {return profession;}

    public String getResidence() {return residence;}

    public LocalDate getDOB() {return DOB;}

    public String getGender() {return gender;}

    public String getEmail() {return email;}

    public LocalDate getJoiningDate() {return joiningDate;}

    // Setters
    public void setName(String name) {this.name = name;}

    public void setSurname(String surname) {this.surname = surname;}

    public void setFavoriteBeer(String favoriteBeer) {this.favoriteBeer = favoriteBeer;}

    public void setProfession(String profession) {this.profession = profession;}

    public void setResidence(String residence) {this.residence = residence;}

    public void setDOB(LocalDate DOB) {this.DOB = DOB;}

    public void setGender(String gender) {this.gender = gender;}

    public void setEmail(String email) {this.email = email;}

    //overrides
    @Override //because entity.User implements Comparable
    public int compareTo(User o) {
        if (name == null) {
            return o.name == null ? 0 : 1;
        }
        else return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", favoriteBeer='" + favoriteBeer + '\'' +
                ", profession='" + profession + '\'' +
                ", residence='" + residence + '\'' +
                ", email='" + email + '\'' +
                ", DOB=" + DOB +
                ", gender='" + gender + '\'' +
                '}';
    }

    // Methods
    // TO DO add entity.Beer to entity.Beer list
    // TO DO add locations to location list
    // TO DO delete user, how to deal with user counter and user ID's?
}
