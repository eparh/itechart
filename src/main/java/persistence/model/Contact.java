package persistence.model;


import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Part;

public class Contact {
    private Long id;
    private String surname;
    private String name;
    private String midName;

    private Date birthday;
    private String gender;
    private String nationality;
    private String site;
    private String email;
    private String company;
    private String maritStatus;
    private Adds address;
    private List<Phone> phones = new ArrayList<>();
    private List<Part> attachs = new ArrayList<>();
    private String photo;




    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMaritStatus() {
        return maritStatus;
    }

    public void setMaritStatus(String maritStatus) {
        this.maritStatus = maritStatus;
    }

    public Adds getAddress() {
        return address;
    }

    public void setAddress(Adds address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }




    public String getFullName() {
        String temp = surname + " " + name;
        if(midName != null){
            temp +=" " + midName;
        }
        return temp;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public void addPhone(Phone phone){
        phones.add(phone);
    }
}
