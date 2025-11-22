package com.sopt.sopkathon.article.dto.response;

import com.sopt.sopkathon.article.domain.Article;

public record ArticleResponse(
    Long articleId,
    String title,
    String imageUrl,
    String link
) {

    public static ArticleResponse from(Article article) {
        return new ArticleResponse(
            article.getId(),
            article.getName(),
            article.getImageUrl(),
            article.getLink()
        );
    }
}
