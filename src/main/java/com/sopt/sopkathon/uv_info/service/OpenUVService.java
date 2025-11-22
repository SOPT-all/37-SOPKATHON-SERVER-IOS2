package com.sopt.sopkathon.uv_info.service;

import com.sopt.sopkathon.uv_info.dto.HomeUvResponse;
import com.sopt.sopkathon.uv_info.dto.OpenUvResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Service
public class OpenUVService {

    private final RestClient restClient;

    private static final String apiKey ="openuv-4uokrmia9k1hq-io";

    private static final String apiUrl="https://api.openuv.io/api/v1/uv";

    public HomeUvResponse getCurrentUv(double lat, double lng) {
        try {
            OpenUvResponse response = restClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path(apiUrl)
                    .queryParam("lat", lat)
                    .queryParam("lng", lng)
                    .build())
                .header("x-access-token", apiKey)
                .retrieve()
                .body(OpenUvResponse.class);

            if (response == null || response.result() == null) {
                throw new IllegalArgumentException("UV 데이터 없음");
            }

            double uv = response.result().uv();
            String description = getDescriptionByUv(uv);

            return HomeUvResponse.from(uv, description);

        } catch (Exception e) {
            // 실패 응답
            return HomeUvResponse.from(0.0, "현재 위치의 자외선 정보를 불러올 수 없습니다.");
        }
    }

    private String getDescriptionByUv(double uv) {
        if (uv < 2.0) return "날씨가 도와주는 날이네요 ! 지금 당장 어디로든 떠나보세요 !!";
        if (uv < 5.0) return "적당한 햇빛! 선크림은 꼭 발라주세요 ☀️";
        return "자외선이 강해요! 장시간 야외활동은 피해주세요.";
    }
}
