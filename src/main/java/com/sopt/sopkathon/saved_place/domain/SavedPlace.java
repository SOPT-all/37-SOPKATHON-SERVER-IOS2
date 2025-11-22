package com.sopt.sopkathon.saved_place.domain;

import com.sopt.sopkathon.common.code.BaseTimeEntity;
import com.sopt.sopkathon.place.domain.Place;
import com.sopt.sopkathon.user.domain.User;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Table(name = "saved_places")
public class SavedPlace extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "place_id", nullable = false)
	private Place place;

	@Column
	private LocalDateTime activatedAt;

	public void activateNow() {
		this.activatedAt = LocalDateTime.now();
	}
}
