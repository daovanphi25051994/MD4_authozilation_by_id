package com.example.demo.config;


import com.example.demo.model.AppUser;
import com.example.demo.repository.IAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class WebSecurity {

    @Autowired
    private IAppUserRepository userRepository;

    public boolean checkUserId(Authentication authentication, String id) {
        String name = authentication.getName();
        AppUser result = userRepository.findByUsername(name);
        return result != null && result.getId().toString().equals(id);
    }
}