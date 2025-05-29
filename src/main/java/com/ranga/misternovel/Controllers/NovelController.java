/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.Controllers;

import com.ranga.misternovel.entities.Novel;
import com.ranga.misternovel.repositories.NovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelRepository novelRepository;

    @GetMapping("")
    public Iterable<Novel> getNovels() {
        return null;
    }

}
