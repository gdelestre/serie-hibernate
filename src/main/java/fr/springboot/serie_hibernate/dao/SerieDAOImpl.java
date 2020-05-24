package fr.springboot.serie_hibernate.dao;

import java.util.List;
import javax.persistence.EntityManager;

import fr.springboot.serie_hibernate.entity.Serie;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SerieDAOImpl implements SerieDAO {
    private EntityManager entityManager;

    @Autowired
    public SerieDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Serie> findSolo() {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Query<Serie> maRequete =
                currentSession.createQuery("SELECT s FROM Serie s where size(s.lesPersonnes) = 1 " +
                        "order by s.prochaineSaison, s.nom", Serie.class);
        List<Serie> lesSeries = maRequete.getResultList();
        return lesSeries;
    }

    public List<Serie> findDuo() {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Query<Serie> maRequete =
                currentSession.createQuery("SELECT s FROM Serie s where size(s.lesPersonnes) = 2 " +
                        "order by s.prochaineSaison, s.nom", Serie.class);
        List<Serie> lesSeries = maRequete.getResultList();
        return lesSeries;
    }

    public Serie findById(int id) {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Serie maSerie = (Serie)currentSession.get(Serie.class, id);
        return maSerie;
    }

    public void saveOrUpdate(Serie maSerie) {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(maSerie);
    }

    public void deleteById(int id) {
        Session currentSession = (Session)this.entityManager.unwrap(Session.class);
        Query maRequete = currentSession.createQuery("delete from Serie where id=:serieId");
        maRequete.setParameter("serieId", id);
        maRequete.executeUpdate();
    }
}

