package com.sj.runner.domain.repository;

import com.sj.runner.domain.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {
    Optional<MemberEntity> findByEmail(String userEmail);

    boolean existsByNickname(String nickname);
    boolean existsByEmail(String email);
}
