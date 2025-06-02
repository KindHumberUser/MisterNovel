/*
 * Created by Sagar Ranga on 2025.5.31
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.Controllers;

import com.ranga.misternovel.dtos.ChapterDto;
import com.ranga.misternovel.entities.Chapter;
import com.ranga.misternovel.mappers.ChapterMapper;
import com.ranga.misternovel.repositories.ChapterRepository;
import com.ranga.misternovel.repositories.NovelRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/novel/{novelId}/chapters")
public class ChapterController {

    private final ChapterRepository chapterRepository;
    private final ChapterMapper chapterMapper;
    private final NovelRepository novelRepository;

    @GetMapping("")
    public List<ChapterDto> getChapters(
            @PathVariable Long novelId
    ) {
        List<Chapter> chapters = chapterRepository.findByNovelId(novelId);
        return chapters.stream().map(chapterMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterDto> getChapter(
            @PathVariable Long id
    ) {
        var chapter = chapterRepository.findById(id).orElse(null);
        if (chapter == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(chapterMapper.toDto(chapter));
    }

    @PostMapping("")
    public ResponseEntity<ChapterDto> createChapter(
            @RequestBody ChapterDto chapterDto,
            @PathVariable Long novelId,
            UriComponentsBuilder uriBuilder
    ) {
        var chapter = chapterMapper.toEntity(chapterDto);
        var novel = novelRepository.findById(novelId).orElse(null);
        if (novel == null) {
            System.out.println("Novel not found");
            return ResponseEntity.notFound().build();
        }
        chapter.setNovel(novel);
        chapterRepository.save(chapter);
        chapterDto.setId(chapter.getId());
        var uri = uriBuilder.path("/novel/{novelId}/chapters/{id}").buildAndExpand(novelId, chapterDto.getId()).toUri();
        return ResponseEntity.created(uri).body(chapterDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterDto> updateChapter(
            @PathVariable Long id,
            @PathVariable Long novelId,
            @RequestBody ChapterDto chapterDto
    ) {
        var chapter = chapterRepository.findById(id).orElse(null);
        if (chapter == null)
            return ResponseEntity.notFound().build();
        var novel = novelRepository.findById(novelId).orElse(null);
        if (novel == null)
            return ResponseEntity.notFound().build();

        chapterMapper.update(chapterDto, chapter);
        chapterRepository.save(chapter);
        novelRepository.save(novel);
        chapterDto.setId(chapter.getId());
        return ResponseEntity.ok(chapterDto);

    }

}