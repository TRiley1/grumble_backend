package com.thomas.grumble.requests;

import lombok.Data;

@Data
public class AddRequest {
    private String username;
    private String content;
    private String subject;

    public AddRequest(String username, String content, String subject) {
        this.username = username;
        this.content = content;
        this.subject = subject;
    }
}
