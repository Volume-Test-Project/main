package com.volumeTest.volume.repository;

import com.volumeTest.volume.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<ConcertEntity, Long> {
}
