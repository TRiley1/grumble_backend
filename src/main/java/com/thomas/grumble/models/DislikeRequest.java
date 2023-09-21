package com.thomas.grumble.models;

import lombok.Data;

@Data
public class DislikeRequest {
    private Long id;
    private String username;
}
