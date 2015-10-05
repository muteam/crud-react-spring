package com.muteam.task_for_svyaznoy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Sms")
public class Sms {

    @Id
    @Column(name = "guid")
    private String id;
    private String phone;
    private String message;
    @Column(name = "sent_date", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sentDate;
    @Column(name = "sent_status")
    private String sentStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getSentStatus() {
        return sentStatus;
    }

    public void setSentStatus(String sentStatus) {
        this.sentStatus = sentStatus;
    }
}
