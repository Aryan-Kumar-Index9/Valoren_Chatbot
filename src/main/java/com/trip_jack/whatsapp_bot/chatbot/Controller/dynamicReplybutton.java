package com.trip_jack.whatsapp_bot.chatbot.Controller;

public class dynamicReplybutton {
    
    private String type;
    private Reply reply;

    public dynamicReplybutton(String type, Reply reply) {
        this.type = type;
        this.reply = reply;
    }

    // Getters and setters (if needed)
    public String getType() { return type; }
    public Reply getReply() { return reply; }
    public void setType(String type) { this.type = type; }
    public void setReply(Reply reply) { this.reply = reply; }
    @Override
        public String toString() {
            return String.format("""
            {
                "type": "%s",
                %s
            }""", this.type, this.reply);
      
   }


}


class Reply {
    private String id;
    private String title;

    public Reply(String id, String title) {
        this.id = id;
        this.title = title;
    }

    // Getters and setters (if needed)
    public String getId() { return id; }
    public String getTitle() { return title; }
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }

        @Override
        public String toString() {
            return String.format("""
            "reply":{
                "id": "%s",
                "title": "%s"
            }""", this.id, this.title);
        }
}

// public class DynamicReplyBttton {
//     String displayText;
//     String url;

//    public DynamicReplyBttton(String displayText, String url) {
//     this.displayText = displayText;
//     this.url = url;
//    }

//    @Override
//    public String toString() {
//         return String.format("""
//         {
//         "display_text": %s,
//         "url": %s
//         }
//         """, this.displayText, this.url);
      
//    }

// }
