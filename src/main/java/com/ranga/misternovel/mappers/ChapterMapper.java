/*
 * Created by Sagar Ranga on 2025.5.31
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.mappers;

import com.ranga.misternovel.dtos.ChapterDto;
import com.ranga.misternovel.entities.Chapter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ChapterMapper {
    @Mapping(target = "novelId", source = "novel.id")
    ChapterDto toDto(Chapter chapter);
    Chapter toEntity(ChapterDto chapterDto);

    @Mapping(target = "id", ignore = true)
    void update(ChapterDto chapterDto, @MappingTarget Chapter chapter);
}
