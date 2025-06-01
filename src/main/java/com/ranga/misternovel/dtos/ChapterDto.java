/*
 * Created by Sagar Ranga on 2025.5.31
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ChapterDto {
    @JsonProperty("chapter_id")
    private Long id;

    private Long novelId;
    private String title;
    private String content;
    private Integer chapterNo;
    private LocalDateTime createdAt;

}
