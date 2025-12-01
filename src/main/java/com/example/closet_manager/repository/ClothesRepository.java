package com.example.closet_manager.repository;

import com.example.closet_manager.domain.item.Clothes;
import com.example.closet_manager.domain.item.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ClothesRepository extends JpaRepository<Clothes, Long> {

    List<Clothes> findAllByMember(Member member);
}
