package com.trip_jack.whatsapp_bot.chatbot.Controller;


// {
//                   "title": "Menu Items1",
//                   "rows": [
//                     {
//                       "id": "text1",
//                       "title": "text1",
//                       "description": "text1"
//                     }
//                   ]
// }
public class listItembutton {
    private String title;
    private Rows row;

    public listItembutton(String title, Rows row){
        this.title=title;
        this.row=row;
    }

    public String getTitle(){return title; }
    public Rows getRow(){return row;}
    public void setTitle(String title){this.title=title;}
    public void setRow(Rows row){this.row=row;}

    @Override
    public String toString(){
        return String.format("""
                {"section_title": "%s",
                %s
    }""", this.title,this.row);
    }
}

// {
//                   "title": "Menu Items1",
//                   "rows": [
//                     {
//                       "id": "text1",
//                       "title": "text1",
//                       "description": "text1"
//                     }
//                   ]
// }
class Rows{
    private String id;
    private String title;
    private String description;

    public Rows(String id, String title, String description){
        this.id=id;
        this.title=title;
        this.description=description;
    }

    public String getID(){return id;}
    public String getTitle(){return title;}
    public String getDescription(){return description;}

    @Override
    public String toString(){
        return String.format("""
                "rows": [
                     {
                       "id": "%s",
                       "title": "%s",
                       "description": "%s"
                     }
                   ]
                """, this.id,this.title,this.description);
    }

}
