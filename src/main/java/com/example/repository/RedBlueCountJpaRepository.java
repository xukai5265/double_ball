package com.example.repository;

import com.example.domain.RedBlueCount;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kaixu on 2018/1/17.
 */
public interface RedBlueCountJpaRepository extends JpaRepository<RedBlueCount,Long> {
}
