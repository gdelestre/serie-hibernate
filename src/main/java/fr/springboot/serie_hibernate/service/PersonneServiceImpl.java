package fr.springboot.serie_hibernate.service;


import java.util.List;

import fr.springboot.serie_hibernate.dao.PersonneDAO;
import fr.springboot.serie_hibernate.entity.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonneServiceImpl implements PersonneService {
    private PersonneDAO personneDAO;

    @Autowired
    public PersonneServiceImpl(PersonneDAO personneDAO) {
        this.personneDAO = personneDAO;
    }

    @Transactional
    public List<Personne> findAll() {
        return this.personneDAO.findAll();
    }

    @Transactional
    public Personne findById(int id) {
        return this.personneDAO.findById(id);
    }
}
