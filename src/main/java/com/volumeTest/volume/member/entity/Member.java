package com.volumeTest.volume.member.entity;


import com.volumeTest.volume.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor//파라미터가 없는 디폴트 생성자를 생성을 함.!
public class Member extends BaseEntity {


    //오늘의 일기 필드명을 쓸때 단어가 끊기면 다음 대문자...?

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//기본키 생성을 데이터 베이스에 위임한다
    private Long id;
    @Column(length = 20,nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String memberName;

    @Column(length = 30,unique = true,updatable = false)//email의 값이 unique해야하고 update가 불가능해야 한다.
    private String email;

    @Builder
    public Member(String password, String memberName, String email) {
        this.password = password;
        this.memberName = memberName;
        this.email = email;


    }

}
