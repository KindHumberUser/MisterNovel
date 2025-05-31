/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.Controllers;

import com.ranga.misternovel.dtos.NovelDto;
import com.ranga.misternovel.entities.Novel;
import com.ranga.misternovel.mappers.NovelMapper;
import com.ranga.misternovel.repositories.GenreRepository;
import com.ranga.misternovel.repositories.NovelRepository;
import com.ranga.misternovel.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/novels")
public class NovelController {

    private final NovelRepository novelRepository;
    private final NovelMapper novelMapper;
    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

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

    @PostMapping("/user/{id}")
    public ResponseEntity<NovelDto> createNovel(
            @RequestBody NovelDto novelDto,
            @PathVariable Long id,
            UriComponentsBuilder uriBuilder
    ) {

        var genre = genreRepository.findById(novelDto.getGenreId()).orElse(null);

        if (genre == null) {
            return ResponseEntity.badRequest().build();
        }

        var novel = novelMapper.toEntity(novelDto);
        novel.setGenre(genre);
        novel.setAuthor(userRepository.findById(id).orElse(null));
        if (novel.getAuthor() == null) {
            return ResponseEntity.badRequest().build();
        }
        novelRepository.save(novel);

        novelDto.setId(novel.getId());
        novelDto.setAuthorId(novel.getAuthor().getId());

        var uri = uriBuilder.path("/novels/{id}").buildAndExpand(novelDto.getId()).toUri();

        return ResponseEntity.created(uri).body(novelDto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<NovelDto> updateNovel(
            @PathVariable Long id,
            @RequestBody NovelDto novelDto
    ) {

        var genre = genreRepository.findById(novelDto.getGenreId()).orElse(null);
        if (genre == null)
            return ResponseEntity.badRequest().build();
        var novel = novelRepository.findById(id).orElse(null);
        if (novel == null)
            return ResponseEntity.notFound().build();

        novelMapper.update(novelDto, novel);
        novel.setGenre(genre);
        novelRepository.save(novel);
        novelDto.setId(novel.getId());
        return ResponseEntity.ok(novelDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNovel(
            @PathVariable Long id) {
        var novel = novelRepository.findById(id).orElse(null);
        if (novel == null)
            return ResponseEntity.notFound().build();
        novelRepository.delete(novel);
        return ResponseEntity.noContent().build();
    }

}
