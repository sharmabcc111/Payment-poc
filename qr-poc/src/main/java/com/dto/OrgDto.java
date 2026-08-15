package com.dto;

import lombok.Data;

import java.time.Instant;

@Data
public class OrgDto {
    Long id;

    String orgName;

    String number;

    String upiId;

    String AccountName;

    Instant createdAt;
}
