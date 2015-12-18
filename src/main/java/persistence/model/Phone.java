package persistence.model;

public class Phone {
    private Long idPhone;
    private String countryCode;
    private String operatorCode;
    private String number;
    private String kind;
    private String comment;
    private Long idContact;

    public Long getIdPhone() {
        return idPhone;
    }

    public void setIdPhone(Long idPhone) {
        this.idPhone = idPhone;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOperatorCode() {
        return operatorCode;
    }

    public void setOperatorCode(String operatorCode) {
        this.operatorCode = operatorCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getFullPhone() {
        return  countryCode + " "+ operatorCode + " " + number;
    }

    @Override
    public String toString() {
        return  countryCode + " "+ operatorCode + " " + number;
    }
}
