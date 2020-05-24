package fr.springboot.serie_hibernate.dao;

import java.util.List;
import javax.persistence.EntityManager;

import fr.springboot.serie_hibernate.entity.Personne;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PersonneDAOImpl implements PersonneDAO {
    private EntityManager entityManager;

    @Autowired
    public PersonneDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Personne> findAll() {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Query<Personne> maRequete = currentSession.createQuery("from Personne", Personne.class);
        List<Personne> lesPersonnes = maRequete.getResultList();
        return lesPersonnes;
    }

    public Personne findById(int id) {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Personne personne = (Personne)currentSession.get(Personne.class, id);
        return personne;
    }
}

