package com.thomas.grumble.requests;

import lombok.Data;

@Data
public class AddRequest {
    private String username;
    private String content;

    public AddRequest(String username, String content) {
        this.username = username;
        this.content = content;
    }
}
