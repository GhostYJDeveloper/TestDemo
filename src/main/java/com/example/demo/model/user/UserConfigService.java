package com.example.demo.model.user;

import com.example.demo.infrastructure.user.UserConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.stereotype.Service;

@Service
@EnableScheduling
public class UserConfigService {
    @Autowired
    private UserConfigRepository userConfigRepository;

    public UserConfig selectByChineseName(String chineseName) {
        UserConfig userConfig = userConfigRepository.selectByChineseName(chineseName);
        return userConfig;
    }

    public User selectByChineseName1(String chineseName){
        User user=userConfigRepository.selectByChineseName1(chineseName);
        return user;
    }
}
