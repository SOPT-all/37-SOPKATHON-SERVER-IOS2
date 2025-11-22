package com.sopt.sopkathon.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sopt.sopkathon.user.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}