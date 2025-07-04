package com.trip_jack.whatsapp_bot.chatbot.Controller;


// {
// "id": "1",
// "title": "Yes"
// }
public class QuickReplyButton {
    private String id;
    private String title;

    public QuickReplyButton(String id, String title){
        this.id=id;
        this.title=title;
    }

    public String getId(){return id;}
    public void setId(String id){this.id=id;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title=title;}
}
