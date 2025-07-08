package com.trip_jack.whatsapp_bot.chatbot.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.trip_jack.whatsapp_bot.chatbot.Controller.QuickReplyButton;
import com.trip_jack.whatsapp_bot.chatbot.Controller.dynamicReplybutton;
import com.trip_jack.whatsapp_bot.chatbot.Controller.listItembutton;


import io.github.cdimascio.dotenv.Dotenv;

@Service
public class netcoreService implements WhatsappVendorInterface {
    
    Dotenv dotenv = Dotenv.load();
    String apiKey= dotenv.get("PEPIPOST_API_KEY");
    String BASE_URL= dotenv.get("PEPIPOST_BASE_URL");
    String TEMPLATE_URL=dotenv.get("TEMPLATE_BASE_URL");
    String botNumber= dotenv.get("BOT_NUMBER");
        
    

    public void postjson(String jsonPayload, String apiKey, String url){
        try{
        HttpClient client= HttpClient.newHttpClient();
        System.out.println(jsonPayload);
        System.out.println(jsonPayload);
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
                .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void sendTextMessage(String recipeint_number, String message_text) {

            String jsonPayload= String.format("""
                    {
                        "phone": "%s",
                        "text": "%s",
                        "enable_acculync": true
                    }""",recipeint_number, message_text);
            postjson(jsonPayload, apiKey, BASE_URL);
    }

    @Override
    public void sendTextReplyTo(String recipient_number, String message_text) {
        String jsonPayload=String.format("""
                {
                    "phone": "{mobile number}",
                    "text": "{sample text}",
                    "reply_to": "wamid.HBgMOTE3MDQ1MzQ1Mjg1FQIAERgSQTg5MkYxNjM3QzU5OExxxxxxxx==",
                    "enable_acculync": true
                }
                """,recipient_number,message_text);
        postjson(jsonPayload, apiKey, BASE_URL);
    }
    
    @Override
    public void sendDocumentImageVideo(String recipientNumber, String mediaType, String url, String fileName,
            String caption) {
        String jsonPayload=String.format("""
                {
                    "phone": "%s",
                    "enable_acculync": true,
                    "media": {
                        "type": "%s",
                        "url": "%s",
                        "file": "s",
                        "caption": "%s"
                    }
                }
                """,recipientNumber,mediaType,url,fileName,caption);
        postjson(jsonPayload, apiKey, BASE_URL);
    }
    
    // @Override
    // public void sendTextMessageCTA(String recipient_number, String recipient_type, String botNumber, String apiHeader,
    //         String header_url, String messageText, ArrayList<String> buttonList) {

    //         String jsonPayload = String.format("""
    //                 {
    //                     "message": [
    //                         {
    //                             "cta_link_track": "true",
    //                             "recipient_whatsapp": "%s",
    //                             "message_type": "interactive",
    //                             "recipient_type": "%s",
    //                             "source": "%s",
    //                             "x-apiheader": "%s",
    //                             "type_interactive": [
    //                                 {
    //                                     "type": "cta_url",
    //                                     "header": {
    //                                         "type": "URL",
    //                                         "url": "%s"
    //                                     },
    //                                     "body": "%s",
    //                                     "action": [
    //                                         {
    //                                             "buttons": %s
    //                                         }
    //                                     ]
    //                                 }                               
    //                             ]
    //                         }
    //                     ]
    //                 }""", recipient_number, recipient_type, botNumber, apiHeader, header_url, messageText, buttonList);
    //         postjson(jsonPayload, apiKey, BASE_URL);
        
    // }


    

    @Override
    public void sendListMessage(String recipientNumber, String headerText, String bodyText, String footerText, String buttonText, List<listItembutton> ListItems) {

            String jsonPayload = String.format("""
                    {
                        "phone": "%s",
                        "enable_acculync": true,
                        "media": {
                            "type": "interactive_list",
                            "header": {
                                "text": "%s"
                            },
                            "body": "%s",
                            "footer_text": "%s",
                            "button_text": "Select",
                            "button": %s
                        }
                    }""", recipientNumber,headerText,bodyText,footerText, ListItems);
            postjson(jsonPayload, apiKey, BASE_URL);
    }

    @Override
    public void sendQuickReplyText(String recipientNumber, String bodyText, String footerText,
            List<QuickReplyButton> Replybuttons) {
        
                String jsonPayload = String.format("""
                    {
                        "phone": "%s",
                        "enable_acculync": true,
                        "media": {
                            "type": "interactive_reply",
                            "body": "%s",
                            "footer_text": "%s",
                            "button": %s
                        }
                    }""", recipientNumber,bodyText,footerText, Replybuttons);
            postjson(jsonPayload, apiKey, BASE_URL);
    }

    @Override
    public void sendQuickReplyButtonMedia(String recipientNumber, String headerType, String headerUrl,
            String headerFileName, String caption, String body, String footerText,
            List<QuickReplyButton> Replybuttons) {
        
            String jsonPayload = String.format("""
                {
                    "phone": "%s",
                    "enable_acculync": true,
                    "media": {
                        "type": "interactive_reply",
                        "header": {
                            "type": "%s",
                            "url": "%s",
                            "file": "%s",
                            "caption": "%s"
                        },
                        "body": "%s",
                        "footer_text": "%s",
                        "button": %s
                    }
                }""", recipientNumber, headerType, headerUrl, headerFileName,caption,body,footerText,Replybuttons);
            postjson(jsonPayload, apiKey, BASE_URL);
    }

//     @Override
//     public void sendDynamicReplyButton(String recipientNumber, String footerText, String bodyText,
//             List<dynamicReplybutton> buttonList) {

//             String jsonPayload = String.format("""
//                     {
//   "message": [
//     {
//       "recipient_whatsapp": "%s",
//       "message_type": "interactive",
//       "recipient_type": "individual",
//       "source": "e4aba266d56a3726c36a2053d70c989d",
//       "x-apiheader": "custom_data",
//       "type_interactive": [
//         {
//           "type": "button",
//           "header": {
//             "type": "image",
//             "url": "https://res.cloudinary.com/demo/image/upload/v1312461204/sample.jpg"
//           },
//           "footer": "%s",
//           "body": "%s",
//           "action": [
//             {
//               "buttons": %s
//             }
//           ]
//         }
//       ]
//     }
//   ]
// }""",recipientNumber,footerText,bodyText,buttonList.toString());
//             postjson(jsonPayload, apiKey, BASE_URL);
//     }

    // @Override
    // public void create_template(String category, String templateName, String language,
    //     String components) {
    //     try{
    //         HttpClient client= HttpClient.newHttpClient();

    //         String jsonPayload= String.format("""
    //                 {
    //                     "category": "%s",
    //                     "name": "%s",
    //                     "language": "%s",
    //                     "allow_category_change": false,
    //                     "components": [%s]
    //                 }
    //                 """,category, templateName,language,components);

    //         System.out.println("Payload being sent: " + jsonPayload);
    //         HttpRequest request = HttpRequest.newBuilder()
    //             .uri(URI.create(TEMPLATE_URL))
    //             .header("Authorization", "Bearer " + apiKey)
    //             .header("Content-Type", "application/json")
    //             .POST(HttpRequest.BodyPublishers.ofString(jsonPayload))
    //             .build();

    //         HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    //         System.out.println("Response Code: " + response.statusCode());
    //         System.out.println("Response Body: " + response.body());

    //         postjson(jsonPayload, apiKey, TEMPLATE_URL);

    //     }
    //     catch (IOException | InterruptedException e) {
    //         e.printStackTrace();
    //     }
    // }

    
    

    

    

    
    
   }
