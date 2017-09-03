package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.PersonEntity;
import com.itbootcamp.starter.datamodel.SkillEntity;
import com.itbootcamp.starter.repository.PersonRepository;
import com.itbootcamp.starter.repository.SkillRepository;
import com.itbootcamp.starter.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/28/2017.
 */

@Service
public class SkillService implements ISkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private PersonRepository personRepository;


    @Override
    public SkillEntity getByName(String name) {
        return skillRepository.getByNameIgnoreCase(name);
    }

    @Override
    public Boolean add(List<SkillEntity> skillEntities, PersonEntity personEntity) {


        List<SkillEntity> bufSkills=new ArrayList<>();

        for(SkillEntity skill:skillEntities) {

            if ((skill.getId()!=null)
                    &&(skillRepository.exists(skill.getId()))) {

                bufSkills.add(skill);

            }

        }

        personEntity.getProfile().setSkills(bufSkills);

        personRepository.save(personEntity);

        return true;
    }

    @Override
    public List<SkillEntity> getAll() {
        return (List) skillRepository.findAll();
    }
}
