package fr.springboot.serie_hibernate.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String prenom;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "lesPersonnes",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JsonBackReference
    private List<Serie> lesSeries;

    public int getId() {
        return this.id;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<Serie> getLesSeries() {
        return this.lesSeries;
    }

    public void setLesSeries(List<Serie> lesSeries) {
        this.lesSeries = lesSeries;
    }

    public Personne() {
    }

    public Personne(@NotNull String prenom, List<Serie> lesSeries) {
        this.prenom = prenom;
        this.lesSeries = lesSeries;
    }

    public String toString() {
        return "Prénom = " + this.prenom;
    }
}
