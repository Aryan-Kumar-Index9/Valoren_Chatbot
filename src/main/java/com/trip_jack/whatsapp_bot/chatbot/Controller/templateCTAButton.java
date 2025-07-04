package com.trip_jack.whatsapp_bot.chatbot.Controller;

// {  
//                                         "type": "URL",
//                                         "text": "Visit Us",
//                                         "url" : "https://google.com"
//                                     },
//                                     {
//                                         "type": "PHONE_NUMBER",
//                                         "text": "Call Us",
//                                         "phone_number": "+919876549114"
//                                     }
public class templateCTAButton {
    private String type;
    private String text;
    private String urlOrPhone;
    private String urlOrPhone_value;

    public templateCTAButton(String type, String text, String urlOrPhone, String urlOrPhone_value){
        this.type=type;
        this.text=text;
        this.urlOrPhone=urlOrPhone;
        this.urlOrPhone_value=urlOrPhone_value;
    }

    public String getType() { return type; }
    public String getText() { return text; }
    public String getUrlOrPhone() { return urlOrPhone; }
    public String getUrlOrPhone_value() { return urlOrPhone_value; }

    public String setType(String type) { return this.type=type; }
    public String setText(String text) { return this.text=text; }
    public String setUrlOrPhone(String urlOrPhone) { return this.urlOrPhone=urlOrPhone; }
    public String setUrlOrPhone_value(String urlOrPhone_value) { return this.urlOrPhone_value=urlOrPhone_value;}

    @Override
        public String toString() {
            return String.format("""
            {  
                "type": "%s",
                "text": "%s",
                 "%s" : "%s"
            }""", this.type, this.text,this.urlOrPhone,this.urlOrPhone_value);
      
    }
}
