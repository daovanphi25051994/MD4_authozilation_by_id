package com.example.demo.formatter;

import com.example.demo.model.AppUser;
import com.example.demo.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;

@Component
public class UserFormatter implements Formatter<AppUser> {

    private IUserService userService;

    @Autowired
    public UserFormatter (IUserService userService) {
        this.userService = userService;
    }

    @Override
    public AppUser parse(String text, Locale locale) throws ParseException {
        return userService.findById(Long.parseLong(text)).get();
    }

    @Override
    public String print(AppUser object, Locale locale) {
        return "[" + object.getId() + " , " + object.getUsername() + ']';
    }
}