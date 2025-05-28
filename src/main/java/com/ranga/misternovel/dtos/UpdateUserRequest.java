/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.dtos;

import lombok.Data;

@Data
public class UpdateUserRequest {
    public String name;
    public String email;
}
