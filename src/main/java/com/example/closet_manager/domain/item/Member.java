package com.example.closet_manager.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) //아이디는 중복되면 안됨
    private String loginId;
    private String password;
    private String name;
}
