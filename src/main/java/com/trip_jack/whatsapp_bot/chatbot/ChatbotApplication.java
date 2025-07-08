package com.trip_jack.whatsapp_bot.chatbot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class ChatbotApplication {

        public static void main(String[] args) {
                Dotenv dotenv = Dotenv.load(); // Loads .env automatically

                // Set env vars so Spring picks them up
                System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
                System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
                System.setProperty("BOT_NUMBER", dotenv.get("BOT_NUMBER"));
                System.setProperty("PEPIPOST_API_KEY", dotenv.get("PEPIPOST_API_KEY"));
                System.setProperty("PEPIPOST_BASE_URL", dotenv.get("PEPIPOST_BASE_URL"));
                System.setProperty("TEMPLATE_BASE_URL", dotenv.get("TEMPLATE_BASE_URL"));
                SpringApplication.run(ChatbotApplication.class, args);
        }


}
