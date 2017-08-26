package com.itbootcamp.starter.services.impl;

import com.itbootcamp.starter.datamodel.impl.*;
import com.itbootcamp.starter.repository.*;
import com.itbootcamp.starter.services.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 8/16/2017.
 */
@Service
public class PersonService implements IPersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private DirectionRepository directionRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private EducationTypeRepository educationTypeRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public PersonEntity getById(Integer personId) {
        return personRepository.findOne(personId);
    }

    @Override
    public PersonEntity getByLogin(String login) {

        return personRepository.findByLogin(login);

    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.findAllByProjectId(projectId);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public List<PersonEntity> getAllPersonsByProjectId(Integer projectId, Boolean isMember) {

        List<PersonEntity> personEntities = new ArrayList<>();

        List<TeamEntity> teamEntities = teamRepository.findAllByProjectIdAndMember(projectId, isMember);

        for (int i = 0; i < teamEntities.size(); i++) {

            personEntities.add(personRepository.findOne(teamEntities.get(i).getPerson().getId()));
        }

        return personEntities;
    }

    @Override
    public PositionEntity getPositionOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {
        PositionEntity positionEntity = new PositionEntity();

        TeamEntity teamEntity = teamRepository.findByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getPosition();
    }

    @Override
    public Boolean getStatusOnProjectByPersonIdAndByProjectId(Integer personId, Integer projectId) {

        TeamEntity teamEntity = teamRepository.findByPersonIdAndProjectId(personId, projectId);

        return teamEntity.getMember();
    }

    private Boolean save(PersonEntity personEntity) {

        if (personRepository.findByLogin(personEntity.getLogin())!=null) {

            return false;


        }

        if ((!roleRepository.exists(personEntity.getRole().getId()))
                || (personEntity.getRole().getId()==1)
                ||(personEntity.getRole().getId()==2)) {

            return false;


        }



        if (!directionRepository.exists(personEntity.getProfile().getDirection().getId())) {

            return false;


        }



        personEntity.getProfile().setId(personEntity.getId());
        personEntity.getContact().setId(personEntity.getId());



        if (personRepository.save(personEntity)!=null) {

            return true;

        } else {

            return false;

        }



    }

    @Override
    public Boolean create(PersonEntity personEntity) {


            personEntity.setId(null);


        List<EducationEntity> educationEntities=personEntity.getProfile().getEducations();

        for (int i=0; i<educationEntities.size(); i++) {

            educationEntities.get(i).setId(null);

            if (!educationTypeRepository.exists(educationEntities.get(i).getEducationTypeEntity().getId())) {

                return false;

            }

        }

        List<CourseEntity> courseEntities=personEntity.getProfile().getCourses();

        for (int i=0; i<courseEntities.size(); i++) {

            courseEntities.get(i).setId(null);

        }

        List<WorkplaceEntity> workplaceEntities=personEntity.getProfile().getWorkplaces();

        for (int i=0; i<workplaceEntities.size(); i++) {

            workplaceEntities.get(i).setId(null);

        }

        personEntity.setBlocked(false);
        personEntity.getProfile().setApproved(false);

        return save(personEntity);





    }

    @Override
    public Boolean update(PersonEntity personEntity) {

        if (!personRepository.exists(personEntity.getId())) {

            return false;


        }

        PersonEntity personInDatabase=personRepository.findOne(personEntity.getId());

     //check educations   ---------------------------------------------------

        List<EducationEntity> educationEntities=personInDatabase.getProfile().getEducations();
        List<EducationEntity> bufEducations=new ArrayList<>();

        Set<Integer> set=new HashSet<>();

        for(int i=0; i<educationEntities.size(); i++) {

            set.add(educationEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getEducations().size(); i++) {


            if ((personEntity.getProfile().getEducations().get(i).getId()==null)
                ||(set.contains(personEntity.getProfile().getEducations().get(i).getId()))) {

                if (!educationTypeRepository.exists(personEntity.getProfile().getEducations()
                        .get(i).getEducationTypeEntity().getId())) {

                    return false;

                }

                bufEducations.add(personEntity.getProfile().getEducations().get(i));

            }

        }

   //----------------------------------------------------

        // check course ---------------------------------------------

        List<CourseEntity> courseEntities=personInDatabase.getProfile().getCourses();
        List<CourseEntity> bufCourses=new ArrayList<>();

        set=new HashSet<>();

        for(int i=0; i<courseEntities.size(); i++) {

            set.add(courseEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getCourses().size(); i++) {


            if ((personEntity.getProfile().getCourses().get(i).getId()==null)
                    ||(set.contains(personEntity.getProfile().getCourses().get(i).getId()))) {

                bufCourses.add(personEntity.getProfile().getCourses().get(i));

            }

        }
        //----------------------------------------------------

        //check workplaces ----------------------------------

        List<WorkplaceEntity> workplaceEntities=personInDatabase.getProfile().getWorkplaces();
        List<WorkplaceEntity> bufWorkplaces=new ArrayList<>();

        set=new HashSet<>();

        for(int i=0; i<workplaceEntities.size(); i++) {

            set.add(workplaceEntities.get(i).getId());

        }

        for(int i=0; i<personEntity.getProfile().getWorkplaces().size(); i++) {


            if ((personEntity.getProfile().getWorkplaces().get(i).getId()==null)
                    ||(set.contains(personEntity.getProfile().getWorkplaces().get(i).getId()))) {

                bufWorkplaces.add(personEntity.getProfile().getWorkplaces().get(i));

            }

        }
        //----------------------------------------------------

        //check skills --------------------------------------

        List<SkillEntity> bufSkills=new ArrayList<>();

        for(int i=0; i<personEntity.getProfile().getSkills().size();i++) {

            if (skillRepository.exists(personEntity.getProfile().getSkills().get(i).getId())) {

                bufSkills.add(personEntity.getProfile().getSkills().get(i));

            }

        }

        //----------------------------------------------------

        personEntity.getProfile().setId(personEntity.getId());
        personEntity.getProfile().setEducations(bufEducations);
        personEntity.getProfile().setCourses(bufCourses);
        personEntity.getProfile().setWorkplaces(bufWorkplaces);
        personEntity.getProfile().setSkills(bufSkills);

        personEntity.getProfile().setApproved(personInDatabase.getProfile().getApproved());
        personEntity.setBlocked(personInDatabase.getBlocked());

        return save(personEntity);
    }


}
