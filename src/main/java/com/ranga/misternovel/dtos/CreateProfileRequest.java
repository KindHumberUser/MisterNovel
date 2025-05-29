/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class CreateProfileRequest {
    private String bio;
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String dateOfBirth;
}
