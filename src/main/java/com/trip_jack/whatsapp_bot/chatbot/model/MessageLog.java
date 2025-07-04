package com.trip_jack.whatsapp_bot.chatbot.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "message_activity_sprintboot")
public class MessageLog {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String direction;
    private String sender;
    private String recipient;
    private String message;
    private LocalDateTime timestamp;

    public Long getId() {return id;}
    public void setId(Long id) {this.id=id;}

    public String getDirection() {return direction;}
    public void setDirection(String direction) {this.direction=direction;}

    public String getSender() {return sender;}
    public void setSender(String sender) {this.sender=sender;}

    public String getRecipient() {return recipient;}
    public void setRecipient(String recipient) {this.recipient=recipient;}

    public String getMessage() {return message;}
    public void setMessage(String message){this.message= message;}

    public LocalDateTime getTimestamp(){return timestamp;}
    public void setTimestamp(LocalDateTime timestamp) {this.timestamp =timestamp;}
}
