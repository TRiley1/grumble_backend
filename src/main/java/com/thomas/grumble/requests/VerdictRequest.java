package com.thomas.grumble.requests;

import lombok.Data;

@Data
public class VerdictRequest {
    private Long grumbleID;
    private String verdict;
}
