package persistence.model;

import java.sql.Date;

public class Attach {
    private Long idAttach;
    private String path;
    private String name;
    private Date  date;
    private String comment;
    private Long idContact;

    public Long getIdAttach() {
        return idAttach;
    }

    public void setIdAttach(Long itAttach) {
        this.idAttach = itAttach;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getIdContact() {
        return idContact;
    }

    public void setIdContact(Long idContact) {
        this.idContact = idContact;
    }
}
