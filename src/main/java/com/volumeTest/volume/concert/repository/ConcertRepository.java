package com.volumeTest.volume.concert.repository;

import com.volumeTest.volume.concert.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertRepository extends JpaRepository<ConcertEntity, Long> {
}
