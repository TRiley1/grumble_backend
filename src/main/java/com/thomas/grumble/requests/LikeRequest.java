package com.thomas.grumble.requests;

import lombok.Data;

@Data
public class LikeRequest {
    private Long id;
    private String username;
}
