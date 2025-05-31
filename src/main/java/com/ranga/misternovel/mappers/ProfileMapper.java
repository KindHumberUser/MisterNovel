/*
 * Created by Sagar Ranga on 2025.5.31
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.mappers;

import com.ranga.misternovel.dtos.ProfileDto;
import com.ranga.misternovel.entities.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    ProfileDto toDto(Profile profile);
    Profile toEntity(ProfileDto profileDto );
    void update(ProfileDto profileDto, @MappingTarget Profile profile);
}
