package com.itbootcamp.starter.webapp.dto;

/**
 * Created by admin on 9/3/2017.
 */
public class VacancyRequestDTO extends AbstractRequestDTO{
    private Boolean isAnswered;
    private VacancyDTO vacancyDTO;

    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    public VacancyDTO getVacancyDTO() {
        return vacancyDTO;
    }

    public void setVacancyDTO(VacancyDTO vacancyDTO) {
        this.vacancyDTO = vacancyDTO;
    }
}
