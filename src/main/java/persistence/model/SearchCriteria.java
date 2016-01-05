package persistence.model;

import java.sql.Date;

public class SearchCriteria {
    private String surname;
    private String name;
    private String midName;
    private Date birthday_from;
    private Date birthday_to;
    private String gender;
    private String nationality;
    private String maritStatus;

    private String country;
    private String city;
    private String address;
    private String index;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday_from() {
        return birthday_from;
    }

    public Date getBirthday_to() {
        return birthday_to;
    }

    public void setBirthday_to(Date birthday_to) {
        this.birthday_to = birthday_to;
    }

    public void setBirthday_from(Date birthday) {
        this.birthday_from = birthday;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMaritStatus() {
        return maritStatus;
    }

    public void setMaritStatus(String maritStatus) {
        this.maritStatus = maritStatus;
    }
}
