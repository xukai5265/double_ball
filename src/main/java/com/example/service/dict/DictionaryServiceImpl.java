package com.example.service.dict;

import com.example.domain.Dictionary;
import com.example.repository.DictionaryJpaRepository;
import com.example.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kaixu on 2018/1/19.
 */
@Service
public class DictionaryServiceImpl {
    @Autowired
    private DictionaryJpaRepository dictionaryJpaRepository;
    @Autowired
    private DictionaryRepository dictionaryRepository;

    /**
     * 添加新单词
     * @param dictionary
     */
    public void saveDict(Dictionary dictionary) {
        dictionaryJpaRepository.save(dictionary);
    }

    /**
     * 检查单词是否已经存在
     * @param word
     * @return
     */
    public boolean isExists(String word){
        return dictionaryRepository.findByWord(word)!=null?true:false;
    }
}
