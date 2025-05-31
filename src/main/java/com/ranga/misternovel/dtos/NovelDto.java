/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NovelDto {
    @JsonProperty("novel_id")
    private Long id;
    private String name;
    private String description;
    private Integer likes;
    private Long authorId;
    private Byte genreId;
    private LocalDateTime createdAt;
}
