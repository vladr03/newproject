package com.vlad.crud.controllers;

import com.vlad.crud.models.Article;
import com.vlad.crud.services.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticlesController {

    private final ArticleService articleService;

    public ArticlesController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("published_articles", articleService.findPublishedArticles());
        model.addAttribute("unpublished_articles", articleService.findUnpublishedArticles());
        model.addAttribute("h1", articleService.h1());
        model.addAttribute("description", articleService.description());
        return "menu";
    }

    @GetMapping("/{id}")
    public String find(@PathVariable(value = "id", required = false) Long id,
                       Model model) {
        model.addAttribute("article", articleService.findByID(id));
        return "content";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(value = "id", required = false) Long id) {
        articleService.deleteById(id);
        return "redirect:/menu";
    }

    @GetMapping("/create")
    public String createArticleForm(Model model) {
        model.addAttribute("article", new Article());
        return "create";
    }

    @PostMapping("/create")
    public String createArticle(@ModelAttribute("article") Article article) {
        articleService.save(article);
        return "redirect:/menu";
    }

    @GetMapping("/update/{id}")
    public String updateArticle(@PathVariable(value = "id", required = false) Long id,
                                Model model) {
        model.addAttribute("article", articleService.findByID(id));
        return "update";
    }

    @PostMapping("/update/{id}")
    public String saveArticle(@ModelAttribute("article") Article article) {
        articleService.save(article);
        return "redirect:/menu";
    }
}
