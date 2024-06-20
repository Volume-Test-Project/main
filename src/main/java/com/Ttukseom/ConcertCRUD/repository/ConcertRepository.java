package com.Ttukseom.ConcertCRUD.repository;

import com.Ttukseom.ConcertCRUD.entity.ConcertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConcertRepository extends JpaRepository <ConcertEntity, Integer> {
    List<ConcertEntity> findAllByModifiedAtDesc(); //ModifiedAt 내림차순 기준으로 모든 데이터를 가져오기 위해, findAllByOrderMyModifiedAtDesc() 메서드를 선언한다.
    Optional<ConcertEntity> findById(int concertId);

}
