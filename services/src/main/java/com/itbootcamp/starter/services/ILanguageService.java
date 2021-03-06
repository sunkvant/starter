package com.itbootcamp.starter.services;

import com.itbootcamp.starter.datamodel.LanguageEntity;

import java.util.List;

/**
 * Created by admin on 8/24/2017.
 */
public interface ILanguageService {
    LanguageEntity getByName(String name);
    List<LanguageEntity> getAll();
}
