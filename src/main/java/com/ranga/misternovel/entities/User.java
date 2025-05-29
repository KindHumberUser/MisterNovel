/*
 * Created by Sagar Ranga on 2025.5.28
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile profile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Address address;

    public void setAddress(Address address) {
        this.address = address;
        address.setUser(this);
    }


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Novel> novels;

    @ManyToMany
    @JoinTable(
            name = "library",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "novel_id")
    )
    private Set<Novel> library = new HashSet<>();


}
