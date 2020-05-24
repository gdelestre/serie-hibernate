package fr.springboot.serie_hibernate.dao;

import fr.springboot.serie_hibernate.entity.Personne;

import java.util.List;

public interface PersonneDAO {
    List<Personne> findAll();

    Personne findById(int id);
}
