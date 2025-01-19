package com.redhun.question_service.dto;

public class OptionRequest {
    public OptionRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    private String text;

    
}
