/*
 * Created by Sagar Ranga on 2025.5.29
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.Controllers;

import com.ranga.misternovel.dtos.*;
import com.ranga.misternovel.entities.Profile;
import com.ranga.misternovel.mappers.UserMapper;
import com.ranga.misternovel.repositories.ProfileRepository;
import com.ranga.misternovel.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ProfileRepository profileRepository;

    @GetMapping("")
    public Iterable<UserDto> getUsers(
            @RequestParam(required = false, defaultValue = "", name = "sort") String sort
    ) {

        if (!Set.of("name", "email").contains(sort))
            sort = "name";

        return userRepository.findAll(Sort.by(sort))
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(
            @RequestBody CreateUserRequest request,
            UriComponentsBuilder uriBuilder) {

        var user = userMapper.toEntity(request);
        userRepository.save(user);

        var userDto = userMapper.toDto(user);
        var uri = uriBuilder.path("/users/{id}").buildAndExpand(userDto.getId()).toUri();
        return ResponseEntity.created(uri).body(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(name = "id") Long id,
            @RequestBody UpdateUserRequest request
    ) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        userMapper.update(request, user);
        userRepository.save(user);

        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        userRepository.delete(user);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/change-password")
    public ResponseEntity<Void> changePassword(
            @PathVariable Long id,
            @RequestBody changePasswordRequest request) {

        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        if (!user.getPassword().equals(request.getOldPassword()))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setPassword(request.getNewPassword());
        userRepository.save(user);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<ProfileDto> getUserProfile(@PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(userMapper.toProfileDto(user.getProfile()));
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<ProfileDto> createUserProfile(
            @RequestBody CreateProfileRequest request,
            @PathVariable Long id) {
        var user = userRepository.findById(id).orElse(null);
        if (user == null)
            return ResponseEntity.notFound().build();
        if (user.getProfile() != null)
            return ResponseEntity.badRequest().build();

        var profile = userMapper.toProfileEntity(request);
        profile.setUser(user);
        System.out.println(profile);
        user.setProfile(profile);
        System.out.println(user.getProfile());
        //profileRepository.save(profile);
        userRepository.save(user);
        return ResponseEntity.ok(userMapper.toProfileDto(profile));
    }

}
