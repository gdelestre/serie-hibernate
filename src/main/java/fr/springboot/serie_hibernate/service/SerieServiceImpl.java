package fr.springboot.serie_hibernate.service;


import fr.springboot.serie_hibernate.dao.SerieDAO;
import fr.springboot.serie_hibernate.entity.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SerieServiceImpl implements SerieService {
    private SerieDAO serieDAO;

    @Autowired
    public SerieServiceImpl(SerieDAO serieDAO) {
        this.serieDAO = serieDAO;
    }

    @Transactional
    public List<Serie> findSolo() {
        return this.serieDAO.findSolo();
    }

    @Transactional
    public List<Serie> findDuo() {
        return this.serieDAO.findDuo();
    }

    @Transactional
    public Serie findById(int id) {
        return this.serieDAO.findById(id);
    }

    @Transactional
    public void saveOrUpdate(Serie maSerie) {
        this.serieDAO.saveOrUpdate(maSerie);
    }

    @Transactional
    public void deleteById(int id) {
        this.serieDAO.deleteById(id);
    }
}

