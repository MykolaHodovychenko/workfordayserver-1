package com.no.company.workfordayserver.services;

import com.no.company.workfordayserver.entities.Vacancy;
import com.no.company.workfordayserver.repos.VacancyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacancyService {

    private VacancyRepository repository;

    @Autowired
    public void setRepository(VacancyRepository repository) {
        this.repository = repository;
    }

    public List<Vacancy> getAllVacancies() {
        return repository.findAll();
    }

    public Optional<Vacancy> getVacancyById(long id) { return repository.findById(id); }

    public void addVacancy(Vacancy s) {
        repository.saveAndFlush(s);
    }

    public void removeVacancy(Vacancy s){ repository.delete(s); }

    public void update(Vacancy vacancy) {
        if (vacancy.getCity() == null)
            repository.updateCity(vacancy.getId(), vacancy.getCity());
        if (vacancy.getDateEnd() == null)
            repository.updateDateEnd(vacancy.getId(), vacancy.getDateEnd());
    }

    public List<Vacancy> getVacancy(Vacancy vacancy) {
        //TODO
        return null;
    }
}