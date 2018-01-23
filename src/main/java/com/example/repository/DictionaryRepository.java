package com.example.repository;

import com.example.domain.Dictionary;
import org.springframework.data.repository.Repository;

/**
 * Created by kaixu on 2018/1/19.
 */
public interface DictionaryRepository extends Repository<Dictionary, Long> {
    Dictionary findByWord(String word);
}
