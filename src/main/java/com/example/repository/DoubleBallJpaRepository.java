package com.example.repository;

import com.example.domain.DoubleBall;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kaixu on 2018/1/11.
 */
public interface DoubleBallJpaRepository extends JpaRepository<DoubleBall,Long> {
}
