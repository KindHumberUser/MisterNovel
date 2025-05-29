/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.Controllers;

import com.ranga.misternovel.dtos.NovelDto;
import com.ranga.misternovel.entities.Novel;
import com.ranga.misternovel.mappers.NovelMapper;
import com.ranga.misternovel.repositories.NovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelRepository novelRepository;
    private final NovelMapper novelMapper;

    @GetMapping("")
    public List<NovelDto> getNovels(
            @RequestParam(required = false, name = "genre") Byte genreId
    ) {
        List<Novel> novels;
        if (genreId != null) {
            novels = novelRepository.findByGenreId(genreId);
        }
        else {
            novels = novelRepository.findAllWithGenre();
        }
        return novels.stream().map(novelMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NovelDto> getNovel(@PathVariable Long id) {
        var novel = novelRepository.findById(id).orElse(null);
        if (novel == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(novelMapper.toDto(novel));
    }

}
