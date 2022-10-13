package com.example.week04n.repository;

import com.example.week04n.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepositroy extends JpaRepository<RefreshToken, String> {
}