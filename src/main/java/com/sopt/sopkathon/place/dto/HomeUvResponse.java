package com.sopt.sopkathon.place.dto;

import com.sopt.sopkathon.uv_info.domain.UvInfo;

public record HomeUvResponse(
    double uv,
    String description
) {

    public static HomeUvResponse of(double uv, UvInfo uvInfo) {
        return new HomeUvResponse(
            uv,
            uvInfo.getMessage()
        );
    }
}
