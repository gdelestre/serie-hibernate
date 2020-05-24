package fr.springboot.serie_hibernate.service;

import fr.springboot.serie_hibernate.entity.Serie;

import java.util.List;

public interface SerieService {
    List<Serie> findSolo();

    List<Serie> findDuo();

    Serie findById(int id);

    void saveOrUpdate(Serie maSerie);

    void deleteById(int id);
}
