package com.trip_jack.whatsapp_bot.chatbot.service;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trip_jack.whatsapp_bot.chatbot.model.UserLog;
import com.trip_jack.whatsapp_bot.chatbot.repository.UserLogRepository;



@Service
public class UserService {
    
    @Autowired
    private UserLogRepository userRepository;

    public UserLog getORcreateuser(String phoneNumber){
        UserLog user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) {
        // New user creation
            user = new UserLog();
            user.setPhoneNumber(phoneNumber);
            user.setUserState("new");
            user.setTimestamp(LocalDateTime.now());
            userRepository.save(user);
        } else if ("Chat ended".equalsIgnoreCase(user.getUserState()) || "Completed".equalsIgnoreCase(user.getUserState())) {
        // Existing user with "Mode selected" → reset to "new"
            user.setUserState("new");
            user.setTimestamp(LocalDateTime.now());
            userRepository.save(user); // UPDATE instead of INSERT
        }
        return user;
    }

    public void updateUserDetails(UserLog user, String name, String email) {
        user.setName(name);
        user.setEmail(email);
        userRepository.save(user);
    }

    public UserLog getUserByPhoneNumber(String userNumber) {
        return userRepository.findByPhoneNumber(userNumber);    
    }

    public void saveUser(UserLog user) {
        
        userRepository.save(user);
    }
}
