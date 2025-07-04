package com.trip_jack.whatsapp_bot.chatbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trip_jack.whatsapp_bot.chatbot.model.UserLog;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long>{

    UserLog findByPhoneNumber(String phoneNumber);

    // Optionally check existence
    boolean existsByPhoneNumber(String phoneNumber);
} 