package com.shixun.ssjserver.dao;

import com.shixun.ssjserver.domain.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleDao extends JpaRepository<ArticleEntity, Integer> {

    Optional<ArticleEntity> findByCode(String code);

    List<ArticleEntity> findAllByUserid(Integer userid);

    List<ArticleEntity> findAllByIsShared(Integer shared);
}
