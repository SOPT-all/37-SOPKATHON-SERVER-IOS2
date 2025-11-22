package com.sopt.sopkathon.uv_info.domain;

import com.sopt.sopkathon.common.code.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "uv_info")
public class UvInfo extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "uv_range", nullable = false)
	private String range;

	@Column(nullable = false)
	private String message;

	@Column(nullable = false)
	private String spf;

	@Column(nullable = false)
	private String pa;

}
