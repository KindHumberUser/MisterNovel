/*
 * Created by Sagar Ranga on 2025.5.28
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chapters")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "novel_id")
    private Novel novel;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "chapter_no")
    private Integer chapterNo;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
