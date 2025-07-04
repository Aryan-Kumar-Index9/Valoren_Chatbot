package com.trip_jack.whatsapp_bot.chatbot.repository;



import com.trip_jack.whatsapp_bot.chatbot.model.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {
    
}

