package com.example.web;

import com.example.domain.Dictionary;
import com.example.domain.User;
import com.example.service.dict.DictionaryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kaixu on 2018/1/19.
 * 单词
 */
@RestController
@RequestMapping(value = "/dict")
public class DictionaryController {
    @Autowired
    private DictionaryServiceImpl dictionaryService;

    @RequestMapping(value = "/add/{word}/{translate}")
    public String addWord(@PathVariable String word,@PathVariable String translate){
        boolean isExists = dictionaryService.isExists(word);
        if(isExists){
            return "exists:"+word;
        }
        Dictionary dictionary = new Dictionary();
        dictionary.setWord(word);
        dictionary.setTranslate(translate);
        dictionaryService.saveDict(dictionary);
        return "success:"+word;
    }
}
