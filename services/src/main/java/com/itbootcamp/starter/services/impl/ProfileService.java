package com.itbootcamp.starter.services.impl;
import com.itbootcamp.starter.repository.ProfileRepository;
import com.itbootcamp.starter.services.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 8/11/2017.
 */
@Service
public class ProfileService implements IProfileService {


    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public Boolean isExists(Integer id) {
        return profileRepository.exists(id);
    }
}
