package com.trip_jack.whatsapp_bot.chatbot.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.trip_jack.whatsapp_bot.chatbot.model.MessageLog;
import com.trip_jack.whatsapp_bot.chatbot.model.UserLog;
import com.trip_jack.whatsapp_bot.chatbot.repository.MessageLogRepository;
import com.trip_jack.whatsapp_bot.chatbot.repository.UserLogRepository;
import com.trip_jack.whatsapp_bot.chatbot.service.UserService;
import com.trip_jack.whatsapp_bot.chatbot.service.netcoreService;

import io.github.cdimascio.dotenv.Dotenv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

    Dotenv dotenv = Dotenv.load();

    String botNumber = dotenv.get("BOT_NUMBER");
    String apiKey = dotenv.get("PEPIPOST_API_KEY");

    @Autowired
    private final netcoreService NetcoreService;
    private final MessageLogRepository messageLogRepository;
    private final UserService userService;
    private UserLogRepository userRepository;
    
    public WebhookController(netcoreService NetcoreService, MessageLogRepository messageLogRepository, UserService userService, UserLogRepository userRepository){
        this.NetcoreService= NetcoreService;
        this.messageLogRepository=messageLogRepository;
        this.userService=userService;
        this.userRepository=userRepository;
    }

    private void logMessage(String sender, String recipient, String messageText, String direction){
        MessageLog message= new MessageLog();
        System.out.println("incoming messsage logging");
        message.setDirection(direction);
        message.setSender(sender);
        message.setRecipient(recipient);
        message.setMessage(messageText);
        message.setTimestamp(LocalDateTime.now());

        messageLogRepository.save(message);
        
    }

    public static boolean containsDigit(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false; // No digit found
    }

    public static boolean containsSpecialCharacter(String str) {
        for (char c : str.toCharArray()) {
            // A character is considered "special" if it's not a letter or a digit.
            if (!Character.isLetterOrDigit(c) && !Character.isWhitespace(c)) {
                return true; // Found a special character (excluding whitespace)
            }
        }
        return false; // No special character found
    }
    @PostMapping()
    public ResponseEntity <String> receiveWebhook(@RequestBody Map<String, Object> payload) {
        
        System.out.println("Received Payload is :" + payload);

        try{
            Map<String, Object> messageData= (Map<String, Object>) ((Map<String, Object>) payload.get("event")).get("message");

            String userMessage= (String) messageData.get("text");
            String userNumber= (String) messageData.get("from");

            System.out.println("User Number: " + userNumber);
            System.out.println("User Message: " + userMessage);
            logMessage(userNumber, botNumber, userMessage,"incoming");


            UserLog user = userService.getORcreateuser(userNumber);
            String message_text;
            
            String currentState = user.getUserState();
            System.out.println(currentState);

            if (currentState == null || currentState.equals("new")) {
                if (userMessage.equalsIgnoreCase("hi") || userMessage.equalsIgnoreCase("hello")) {
                    message_text="Hi, Welcome to the TRIP JACK!!!!!!!!!!!!!!!";
                    logMessage(botNumber, userNumber, message_text, "outgoing");
                    NetcoreService.sendTextMessage(userNumber, message_text);
                    message_text="What is your Name?";
                    logMessage(botNumber, userNumber, message_text, "outgoing");
                    NetcoreService.sendTextMessage(userNumber, message_text);
                    user.setUserState("Asked name");
                    userRepository.save(user);
                    //trailTemplate.create_template_trail();
                    // fetch_template_details.fetch_template_details_function("226799", "aryan_s_sample_template");
                    // delete_Trail_template.delete_trail_template_function("aryan_s_sample_template");
                    return ResponseEntity.ok("asked_name");
                } 
                
                else {
                    message_text="Please type \"hi\" or \"hello\" to start the conversation.";
                    logMessage(botNumber, userNumber, message_text, "outgoing");
                    NetcoreService.sendTextMessage(userMessage, message_text);
                    return ResponseEntity.ok("invalid_start");
                }
            }
            else if(currentState.equalsIgnoreCase("asked name")){
                if ( containsDigit(userMessage)== false && containsSpecialCharacter(userMessage)== false ){
                    user.setName(userMessage);
                    user.setUserState("Asking Gmail");
                    message_text="Name Saved. What is your gmail??";
                    NetcoreService.sendTextMessage(userNumber, message_text);
                    logMessage(botNumber, userNumber, message_text, "outgoing");
                    userRepository.save(user);
                    return ResponseEntity.ok("Name has been saved and Gmail has been asked");
                }
                else{
                    message_text="Invalid name. Please enter the valid name with no number and no speacial characters!";
                    NetcoreService.sendTextMessage(userNumber, message_text);
                    logMessage(botNumber, userNumber, message_text, "outgoing");
                    return ResponseEntity.ok("Entered the invalid name & asked for the name again.");
                }
            }
            else if(currentState.equalsIgnoreCase("Asking Gmail")){
                user.setEmail(userMessage);
                message_text="Mail have been saved. Tell me from where do you want to go and from where?";
                NetcoreService.sendTextMessage(userNumber, message_text);
                logMessage(botNumber, userNumber, message_text, "outgoing");
                user.setUserState("Asking location");
                userRepository.save(user);
                return ResponseEntity.ok("Gmail saved and asking for location.");
            }

            else if(currentState.equalsIgnoreCase("Asking Location")){
                user.setFromTo(userMessage);
                message_text="Arrival and departure location has been saved!";
                NetcoreService.sendTextMessage(userNumber, message_text);
                logMessage(botNumber, userNumber, message_text, "outgoing");
                message_text="Dates at which you want to travel.";
                NetcoreService.sendTextMessage(userNumber, message_text);
                logMessage(botNumber, userNumber, message_text, "outgoing");
                user.setUserState("Asking Dates");
                userRepository.save(user);
                return ResponseEntity.ok("Location saved and asking for dates");
            }

            else if (currentState.equalsIgnoreCase("Asking Dates")){
                user.setStartEndDate(userMessage);
                message_text="Dates has been saved!.";
                NetcoreService.sendTextMessage(userNumber, message_text);
                logMessage(botNumber, userNumber, message_text, "outgoing");
                message_text="Dates has been saved. Thankyou for your time.";
                NetcoreService.sendTextMessage(userNumber, message_text);
                logMessage(botNumber, userNumber, message_text, "outgoing");
                user.setUserState("Completed");
                userRepository.save(user);
                return ResponseEntity.ok("Chat ended");
            }
            return ResponseEntity.ok("Success");
        }
        catch (Exception e){
                System.out.println("Error while parsing:" + e.getMessage());
                return ResponseEntity.badRequest().body("Invalid request Structure");
        }
    
    }
}
//System.out.println(userMessage.length());
            // ArrayList<String> buttonList = new ArrayList<>();
            // String button="{\r\n" + //
            //                     "                               \"name\": \"shop_now\",\r\n" + //
            //                     "                               \"parameters\": {\r\n" + //
            //                     "                                       \"display_text\": \"Shop Now\",\r\n" + //
            //                     "                                       \"url\": \"https://yourbrand.com/offer\"\r\n" + //
            //                     "                                       }\r\n" + //
            //                     "                               }";
            // buttonList.add(button);

            // List<listItembutton> listbutton= new ArrayList<>();
            // listbutton.add(new listItembutton("Option 1", new Rows("1", "Flight", "fastest")));
            // listbutton.add(new listItembutton("Option 2", new Rows("2", "Train", "Comfort")));
            // listbutton.add(new listItembutton("Option 3", new Rows("3", "Bus", "Affordable")));

            // List<dynamicReplybutton> replyButtonList = new ArrayList<>();

            // replyButtonList.add(new dynamicReplybutton("reply", new Reply("1", "Flight")));
            // replyButtonList.add(new dynamicReplybutton("reply", new Reply("2", "Train")));
            // replyButtonList.add(new dynamicReplybutton("reply", new Reply("3","Bus")));
            // //System.out.println(replyButtonList);
            
            // List<String> bodyExample = new ArrayList<>();
            // bodyExample.add("\"8458aha\"");
            // bodyExample.add("\"njf\"");
            // List<String> buttontilelist=new ArrayList<>();
            // buttontilelist.add("bbb");
            // buttontilelist.add("aaa");
            // TemplateComponent header= TemplateComponent.createHeader("image", "kjlvndlkvnadkjlnvjla");
            // TemplateComponent body = TemplateComponent.createBody("i am a body{{1}}", bodyExample);
            // TemplateComponent footer = TemplateComponent.createFooter("i am footer");
            // TemplateComponent buttons= TemplateComponent.createButtons(buttontilelist);
            //System.out.println(header);
            //System.out.println(body);
            //System.out.println(footer);
            //System.out.println(buttons);
            //String components= header +""+ body + footer + buttons;
            // List<templateCTAButton> templateButton = new ArrayList<>();
            // templateButton.add(new templateCTAButton("URL", "Visit Us", "url", "https://www.google.com"));
            // NetcoreService.create_template("UTILITY", "aryans_template1", "en", components);
            // NetcoreService.create_template("UTILITY", "sample_aryan_003","en", "{{1}} okay ", "Join ining", "i am test footer okay okay", templateButton);
            //NetcoreService.sendTextMessage(userNumber, userMessage, botNumber);
            // NetcoreService.sendTextMessageCTA(userNumber, "individual", botNumber, "your-header", "https://my-experiment-buckent.s3.ap-south-1.amazonaws.com/Screenshot+2025-06-17+154611.png", "Hi bro", buttonList );
            // NetcoreService.sendMediaMessage(userNumber, botNumber, "image", "https://my-experiment-buckent.s3.ap-south-1.amazonaws.com/Screenshot+2025-06-17+154611.png", "Hi this media", "img001");
            // NetcoreService.sendListMessage(userNumber, "text", "i am text", "i am footer","i am body", listbutton);
            // NetcoreService.sendDynamicReplyButton(userNumber, "this is a footer made by aryan", "This is a body made by aryan", replyButtonList);
            



