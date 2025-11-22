package com.sopt.sopkathon.uv_info.dto;

public record HomeUvResponse(
    double uv,
    String description
) {
    public static HomeUvResponse from(double uv, String description) {
        return new HomeUvResponse(uv, description);
    }
}
