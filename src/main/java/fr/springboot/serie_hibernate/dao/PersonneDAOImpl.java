package fr.springboot.serie_hibernate.dao;

import fr.springboot.serie_hibernate.entity.Personne;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PersonneDAOImpl implements PersonneDAO {
    private EntityManager entityManager;

    @Autowired
    public PersonneDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Personne> findAll() {
        Session currentSession = this.entityManager.unwrap(Session.class);
        Query<Personne> maRequete = currentSession.createQuery("from Personne", Personne.class);
        List<Personne> lesPersonnes = maRequete.getResultList();
        return lesPersonnes;
    }

    public Personne findById(int id) {
        Session currentSession = this.entityManager.unwrap(Session.class);
        Personne personne = currentSession.get(Personne.class, id);
        return personne;
    }
}

