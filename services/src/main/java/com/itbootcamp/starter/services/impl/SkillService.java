package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.PersonEntity;
import com.itbootcamp.starter.datamodel.impl.RoleEntity;
import com.itbootcamp.starter.datamodel.impl.SkillEntity;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.repository.RoleRepository;
import com.itbootcamp.starter.repository.SkillRepository;
import com.itbootcamp.starter.services.ISkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 7/28/2017.
 */

@Service
public class SkillService implements ISkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private ProfileRepository profileRepository;



    @Override
    public Boolean add(List<SkillEntity> skillEntities, PersonEntity personEntity) {


        List<SkillEntity> bufSkills=new ArrayList<>();

        for(int i=0; i<skillEntities.size();i++) {

            if (skillRepository.exists(skillEntities.get(i).getId())) {

                bufSkills.add(skillEntities.get(i));

            }

        }

        personEntity.getProfile().setSkills(bufSkills);

        profileRepository.save(personEntity.getProfile());

        return true;
    }
}
