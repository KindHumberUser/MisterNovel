/*
 * Created by Sagar Ranga on 2025.5.28
 * Copyright © 2025 Sagar Ranga. All rights reserved.
 */

package com.ranga.misternovel.repositories;

import com.ranga.misternovel.entities.Chapter;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {

    @EntityGraph(attributePaths = {"novel"})
    @Query("SELECT c FROM Chapter c")
    List<Chapter> findByNovelId(Long novelId);

}
