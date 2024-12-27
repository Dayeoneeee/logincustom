package com.woori.logincustom.Repository;

import com.woori.logincustom.Entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer> {
    Optional<LoginEntity> findByLoginid(String loginid);
}
