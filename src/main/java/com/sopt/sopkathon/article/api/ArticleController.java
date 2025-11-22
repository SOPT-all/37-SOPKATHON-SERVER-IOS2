package com.sopt.sopkathon.article.api;

import static com.sopt.sopkathon.common.code.SuccessCode.ARTICLE_GET_SUCCESS;

import com.sopt.sopkathon.article.dto.response.ArticleResponse;
import com.sopt.sopkathon.article.service.ArticleService;
import com.sopt.sopkathon.common.dto.ApiResponseBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/home/article")
    public ResponseEntity<ApiResponseBody<ArticleResponse>> getArticle() {
        return ResponseEntity.status(200)
                .body(ApiResponseBody.onSuccess(ARTICLE_GET_SUCCESS, articleService.getArticle()));
    }
}
