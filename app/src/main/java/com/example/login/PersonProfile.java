package com.example.login;

public class PersonProfile {
    String personName;
    String personAge;
    String personReligion;
    String personCast;
    String personLocation;
    String personOccupation;
    String personImg;

    public PersonProfile(String personImg, String personName, String personAge, String personReligion, String personCast, String personLocation, String personOccupation) {
        this.personImg = personImg;
        this.personName = personName;
        this.personAge = personAge;
        this.personReligion = personReligion;
        this.personCast = personCast;
        this.personOccupation = personOccupation;
        this.personLocation = personLocation;

    }
    public String getPersonImg() {
        return personImg;
    }
    public void setPersonImg(String personImg) {
        this.personImg = personImg;
    }

    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonAge() {
        return personAge;
    }
    public void setPersonAge(String personAge) {
        this.personAge = personAge;
    }

    public String getPersonReligion() {
        return personReligion;
    }
    public void setPersonReligion(String personReligion) {
        this.personReligion = personReligion;
    }

    public String getPersonCast() {
        return personCast;
    }
    public void setPersonCast(String personCast) {
        this.personCast = personCast;
    }

    public String getPersonLocation() {
        return personLocation;
    }
    public void setPersonLocation(String personLocation) {
        this.personLocation = personLocation;
    }

    public String getPersonOccupation() {
        return personOccupation;
    }
    public void setPersonOccupation(String personOccupation) {
        this.personOccupation = personOccupation;
    }

}
