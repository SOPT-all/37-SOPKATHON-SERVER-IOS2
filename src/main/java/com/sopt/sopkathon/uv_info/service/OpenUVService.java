package com.sopt.sopkathon.uv_info.service;

import com.sopt.sopkathon.uv_info.dto.OpenUvResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Service
public class OpenUVService {

    private final RestClient restClient;

    private static final String apiKey = "openuv-4uokrmia9k1hq-io";

    public double getCurrentUv(double lat, double lng) {
        OpenUvResponse response = restClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/api/v1/uv")
                .queryParam("lat", lat)
                .queryParam("lng", lng)
                .build())
            .header("x-access-token", apiKey)
            .retrieve()
            .body(OpenUvResponse.class);

        if (response == null || response.result() == null) {
            throw new IllegalArgumentException("UV 데이터 없음");
        }

        return response.result().uv();
    }
}
