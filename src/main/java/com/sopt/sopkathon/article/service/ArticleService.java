package com.sopt.sopkathon.article.service;

import com.sopt.sopkathon.article.domain.Article;
import com.sopt.sopkathon.article.dto.response.ArticleResponse;
import com.sopt.sopkathon.article.repository.ArticleRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleResponse getArticle() {
        Article article =  articleRepository.findById(1L).get();
        return ArticleResponse.from(article);
    }

}
