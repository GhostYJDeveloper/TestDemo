package com.example.demo.domain.profile;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

public class DemoBean {

    private String content;

    public DemoBean(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
