package com.example.closet_manager.repository;

import com.example.closet_manager.domain.item.Clothes;
import com.example.closet_manager.domain.item.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByLoginId(String loginId);

}
