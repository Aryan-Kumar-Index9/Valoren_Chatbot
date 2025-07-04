package com.trip_jack.whatsapp_bot.chatbot.Controller;

import java.util.List;

public class TemplateComponent {
    
    private String type;
    private String format;
    private String example;
    private String text;
    private List<String> buttons; 
    private List<String> bodyExample;

    public static TemplateComponent createHeader(String format, String exampleHandle){
        TemplateComponent comp = new TemplateComponent();
        comp.type="header";
        comp.format=format;
        comp.example=exampleHandle;
        return comp;
        
    }

    public static TemplateComponent createBody(String text, List<String> exampleBody) {
        TemplateComponent comp = new TemplateComponent();
        comp.type = "body";
        comp.text = text;
        comp.bodyExample= exampleBody;
        return comp;
    }

    public static TemplateComponent createFooter(String text) {
        TemplateComponent comp = new TemplateComponent();
        comp.type = "footer";
        comp.text = text;
        return comp;
    }

    public static TemplateComponent createButtons(List<String> replybuttonTitles) {
        TemplateComponent comp = new TemplateComponent();
        comp.type = "button";
        comp.buttons = replybuttonTitles;
        return comp;
    }

    @Override
    public String toString(){
        return switch(type){
            case "header" -> String.format("""
                        {
          "type": "header",
          "format": "%s",
          "example": {
            "header_handle": [
              "%s"
            ]
          }
        },
                    """,format,example);
            
            case "body" -> String.format("""
                    {
                        "type": "%s",
                        "text": "%s",
                        "example": {
                            "body_text": [
                                %s
                            ]
                        }
                    },
                    """,type,text,bodyExample);
            
            case "footer" -> String.format("""
                    {
                        "type":"%s",
                        "text":"%s"
                    },
                    """,type,text );
            
            case "button" -> buildButtonString();

            default -> "{}";
        };
    }

    private String buildButtonString(){
        StringBuilder buttonJson = new StringBuilder("""
            {
                "type": "button",
                "buttons": [
        """);

        for (int i = 0; i < buttons.size(); i++) {
            String btn = buttons.get(i);
            buttonJson.append(String.format("""
                {
                    "type": "reply",
                    "reply": {
                        "id": "%d",
                        "title": "%s"
                    }
                }""", i + 1, btn));
            if (i < buttons.size() - 1) {
                buttonJson.append(",");
            }
        }

        buttonJson.append("\n        ]\n    }");
        return buttonJson.toString();
    }

}
