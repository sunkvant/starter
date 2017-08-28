package com.itbootcamp.starter.datamodel;

import javax.persistence.*;

/**
 * Created by admin on 8/8/2017.
 */
@Entity
@Table(name = "vacancy_request")
public class VacancyRequestEntity extends AbstractEntityID {
    private Boolean isAnswered;
    private VacancyEntity vacancy;

    @Column(name = "is_answered", nullable = false)
    public Boolean getAnswered() {
        return isAnswered;
    }

    public void setAnswered(Boolean answered) {
        isAnswered = answered;
    }

    @ManyToOne
    @JoinColumn(name = "vacancy_id", referencedColumnName = "id", nullable = false)
    public VacancyEntity getVacancy() {
        return vacancy;
    }

    public void setVacancy(VacancyEntity vacancy) {
        this.vacancy = vacancy;
    }
}
