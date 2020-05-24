package fr.springboot.serie_hibernate.service;

import fr.springboot.serie_hibernate.entity.Personne;

import java.util.List;

public interface PersonneService {
    List<Personne> findAll();

    Personne findById(int id);
}
