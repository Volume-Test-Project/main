package com.volumeTest.volume.member.entity;

import com.volumeTest.volume.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20,nullable = false)
    private String password;

    @Column(length = 30, nullable = false, unique = true, updatable = false)
    private String email;

    @Column(length = 20, nullable = false)
    private String memberName;

    private LocalDateTime deletedAt;

    @Builder
    public Member(String password, String email, String memberName) {
        this.password = password;
        this.email = email;
        this.memberName = memberName;
    }
}
