package com.sopt.sopkathon.uv_info.service;

import com.sopt.sopkathon.uv_info.domain.UvInfo;
import com.sopt.sopkathon.uv_info.repository.UVInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UvInfoService {

    private final UVInfoRepository uvInfoRepository;

    public UvInfo getUvInfoByValue(double uv) {
        List<UvInfo> uvInfos = uvInfoRepository.findAll();

        for (UvInfo info : uvInfos) {
            String range = info.getRange();

            if (range.contains("+")) {
                double min = Double.parseDouble(range.replace("+", ""));
                if (uv >= min) {
                    return info;
                }
            } else {
                String[] split = range.split("-");
                double min = Double.parseDouble(split[0]);
                double max = Double.parseDouble(split[1]);
                if (uv >= min && uv <= max) {
                    return info;
                }
            }
        }
        return null;
    }

}
