package com.example.CounselingTree.repositories;

import com.example.CounselingTree.entities.EnumerationLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnumerationLevelRepository extends JpaRepository<EnumerationLevel, Long> {
    Optional<EnumerationLevel> findByDescription(String description);
    Optional<EnumerationLevel> findByCode(String code);
}
