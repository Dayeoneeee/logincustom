package com.woori.logincustom.Repository;

import com.woori.logincustom.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
    //사용자id로 조회하는 쿼리 추가
    Optional<LoginEntity> findByLoginid(String loginid);
}
