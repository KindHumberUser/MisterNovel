/*
 * Created by Sagar Ranga on 2025.5.28
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.repositories;

import com.ranga.misternovel.entities.Novel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NovelRepository extends JpaRepository<Novel, Long> {

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Novel> findByGenreId(Byte genreId);

    @EntityGraph(attributePaths = {"author", "genre"})
    @Query("SELECT n FROM Novel n")
    List<Novel> findAllWithGenre();

}
