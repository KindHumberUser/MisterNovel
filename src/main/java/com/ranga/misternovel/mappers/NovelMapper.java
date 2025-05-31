/*
 * Created by Sagar Ranga on 2025.5.30
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.mappers;

import com.ranga.misternovel.dtos.NovelDto;
import com.ranga.misternovel.entities.Novel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface NovelMapper {

    @Mapping(target = "authorId", source = "author.id")
    @Mapping(target = "genreId", source = "genre.id")
    NovelDto toDto(Novel novel);

    Novel toEntity(NovelDto novelDto);

    @Mapping(target = "id", ignore = true)
    void update(NovelDto novelDto, @MappingTarget Novel novel);
}
