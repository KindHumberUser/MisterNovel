/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.mappers;

import com.ranga.misternovel.dtos.*;
import com.ranga.misternovel.entities.Profile;
import com.ranga.misternovel.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(CreateUserRequest request);
    void update(UpdateUserRequest request, @MappingTarget User user);
    ProfileDto toProfileDto(Profile profile);
    Profile toProfileEntity(CreateProfileRequest request);
    //void updateProfile();
}
