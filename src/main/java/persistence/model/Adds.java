package persistence.model;

public class Adds {
    private String country;
    private String city;
    private String address;
    private String index;
    private Long idAddress;

    @Override
    public String toString() {
        String temp = "";

        if(country != null && !"".equals(country)) temp += country;
        if (city != null && !"".equals(city)){
            if(! temp.equals("")) temp += ", ";
            temp += city;
        }
        if (address != null && !"".equals(address)){
            if(! temp.equals("")) temp +=", ";
            temp += address;
        }
        if (index != null && !"".equals(index)){
            if(! temp.equals("")) temp +=", ";
            temp += index;
        }
        return temp;
    }

    public Long getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(Long idAddress) {
        this.idAddress = idAddress;
    }

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
