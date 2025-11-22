package com.sopt.sopkathon.uv_info.service;

import com.sopt.sopkathon.uv_info.domain.UvInfo;
import com.sopt.sopkathon.uv_info.repository.UVInfoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UvInfoService {

    private final UVInfoRepository uvInfoRepository;

    public UvInfo getUvInfoByValue(double uv) {
        List<UvInfo> uvInfos = uvInfoRepository.findAll();
        long formattedUv = Math.round(uv);

        for (UvInfo info : uvInfos) {
            String range = info.getRange();
            log.info("range:{}", range);
            if (range.contains("+")) {
                int min = Integer.parseInt(range.replace("+", ""));
                log.info("min:{}", min);
                if (formattedUv >= min) {
                    log.info("uv:{}, min:{}", formattedUv, min);
                    return info;
                }
            } else {
                String[] split = range.split("-");
                int min = Integer.parseInt(split[0]);
                log.info("min:{}", min);
                int max = Integer.parseInt(split[1]);
                log.info("max:{}", max);
                if (formattedUv >= min && formattedUv <= max) {
                    log.info("min:{}, uv:{}, max:{}", min, formattedUv, max);
                    return info;
                }
            }
        }
        return null;
    }

}
