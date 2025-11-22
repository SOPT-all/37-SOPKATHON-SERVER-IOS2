package com.sopt.sopkathon.uv_info.repository;

import com.sopt.sopkathon.uv_info.domain.UvInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UVInfoRepository extends JpaRepository<UvInfo, Long> {

}
