package com.example.closet_manager.service;

import com.example.closet_manager.domain.item.Clothes;
import com.example.closet_manager.domain.item.Member;
import com.example.closet_manager.repository.ClothesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothesService {
    private final ClothesRepository clothesRepository;

    //옷 등록
    public void saveClothes(Clothes clothes, Member member) {
        clothes.setMember(member);
        clothesRepository.save(clothes);
    }

    //옷장 보기
    public List<Clothes> findMyClothes(Member member) {
        return clothesRepository.findAllByMember(member);
    }

    //옷 상세/수정 조회
    public Clothes findOne(Long id) {
        return clothesRepository.findById(id).orElse(null);
    }

    //옷 수정
    @Transactional
    public void updateClothes(Long id, String name, String category, String brand, String color, String imageUrl) {
        Clothes clothes = clothesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 옷이 없습니다. id=" + id));

        clothes.setName(name);
        clothes.setCategory(category);
        clothes.setBrand(brand);
        clothes.setColor(color);
        clothes.setImageUrl(imageUrl);
    }

    /**
     * 옷 삭제(delete)
     * 데이터가 변하는 작업이기 때문에 @Transactional 필요
     */
    @Transactional
    public void deleteClothes(Long id) {
        clothesRepository.deleteById(id);
    }
}
