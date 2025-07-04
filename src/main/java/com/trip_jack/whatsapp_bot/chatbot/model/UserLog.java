package com.trip_jack.whatsapp_bot.chatbot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserLog{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;
    private String name;
    private LocalDateTime timestamp = LocalDateTime.now(); 
    private String userState;
    private String email;
    private String fromTo;
    private String startEndDate;

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String getUserState() { return userState; }
    public void setUserState(String userState) { this.userState = userState; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFromTo(){return fromTo;}
    public void setFromTo(String fromTo){this.fromTo=fromTo;}

    public String getStartEndDate(){return startEndDate;}
    public void setStartEndDate(String startEndDate){this.startEndDate=startEndDate;}

}

