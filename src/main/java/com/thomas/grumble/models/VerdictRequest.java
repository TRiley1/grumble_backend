package com.thomas.grumble.models;

import lombok.Data;

@Data
public class VerdictRequest {
    private Long grumbleID;
    private String verdict;
}
