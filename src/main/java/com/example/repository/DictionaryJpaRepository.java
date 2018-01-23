package com.example.repository;

import com.example.domain.Dictionary;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by kaixu on 2018/1/19.
 */
public interface DictionaryJpaRepository extends JpaRepository<Dictionary,Long> {
}
