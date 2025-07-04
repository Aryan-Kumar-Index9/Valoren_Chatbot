package com.trip_jack.whatsapp_bot.chatbot.service;

import java.util.ArrayList;
import java.util.List;

import com.trip_jack.whatsapp_bot.chatbot.Controller.QuickReplyButton;
import com.trip_jack.whatsapp_bot.chatbot.Controller.dynamicReplybutton;
import com.trip_jack.whatsapp_bot.chatbot.Controller.listItembutton;

interface WhatsappVendorInterface {

    public void sendTextMessage(
        String recipeintNumber, 
        String messageText
    );

    public void sendTextReplyTo(
        String recipientNumber,
        String messageText
    );

    public void sendDocumentImageVideo(
        String recipientNumber,
        String mediaType,
        String url,
        String fileName,
        String caption
    );

    // // public void sendTextMessageCTA(
    //     String recipient_number, 
    //     String recipient_type, 
    //     String botNumber, 
    //     String apiHeader, 
    //     String header_url, 
    //     String messageText,
    //     ArrayList<String> buttonList
    // );

    public void sendQuickReplyText(
        String recipientNumber,
        String bodyText,
        String footerText,
        List<QuickReplyButton> Replybuttons
    );

    public void sendQuickReplyButtonMedia(
        String recipientNumber,
        String headerType,
        String headerUrl,
        String headerFileName,
        String caption,
        String body,
        String footerText,
        List<QuickReplyButton> Replybuttons
    );

    public void sendListMessage(
        String recipientNumber,
        String headerText,
        String bodyText,
        String footerText,
        String buttonText, 
        List<listItembutton> ListItems
    );

    

    // public void sendDynamicReplyButton(
    //     String recipientNumber,
    //     String footerText,
    //     String bodyText,
    //     List<dynamicReplybutton> buttonList
    // );
    

    // public void create_template(
    //     String category,
    //     String templateName,
    //     String language,
    //     String components
    // );
}
