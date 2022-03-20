package com.vlad.crud.repository;

import com.vlad.crud.models.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}