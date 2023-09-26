package com.thomas.grumble.requests;

import lombok.Data;

@Data
public class DislikeRequest {
    private Long id;
    private String username;
}
