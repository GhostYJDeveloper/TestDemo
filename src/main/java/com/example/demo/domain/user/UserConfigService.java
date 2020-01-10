package com.example.demo.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
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
