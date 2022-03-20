package com.vlad.crud.services;

import com.vlad.crud.models.Article;
import com.vlad.crud.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    Article article = new Article();
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }


    public List<Article> findPublishedArticles() {
        List<Article> articles = articleRepository.findAll();
        List<Article> published_articles = new ArrayList<>();
        for (Article article : articles) {
            if (!new Date().before(article.getPublished_at()))
                published_articles.add(article);
        }
        return published_articles;
    }

    public List<Article> findUnpublishedArticles() {
        List<Article> articles = articleRepository.findAll();
        List<Article> unpublished_articles = new ArrayList<>();
        for (Article article : articles) {
            if (new Date().before(article.getPublished_at()))
                unpublished_articles.add(article);
        }
        return unpublished_articles;
    }

    public Article findByID(Long id) {
        return articleRepository.findById(id).orElse(new Article());
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    public void save(Article article) {
        articleRepository.save(article);
    }

    public String h1() {
        article.setH1("Статьи");
        return article.getH1();
    }

    public String description() {
        article.setDescription("Просмотр статей");
        return article.getDescription();
    }

}
