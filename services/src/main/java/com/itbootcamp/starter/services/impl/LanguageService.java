package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.LanguageEntity;
import com.itbootcamp.starter.repository.LanguageRepository;
import com.itbootcamp.starter.services.ILanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/24/2017.
 */
@Service
public class LanguageService implements ILanguageService {
    @Autowired
    private LanguageRepository languageRepository;

    @Override
    public LanguageEntity getByName(String name){
        return languageRepository.getByNameIgnoreCase(name);
    }
}
