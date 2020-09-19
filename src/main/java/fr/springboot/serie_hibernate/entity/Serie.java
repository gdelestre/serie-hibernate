package fr.springboot.serie_hibernate.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name = "series")
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 3, message = "Indiquer le nom de la série")
    @NotBlank(message = "Erreur : que des blancs ont été saisis")
    private String nom;

    @Size(min = 8, message = "Indiquer : Saison ...")
    @NotBlank(message = "Erreur : que des blancs ont été saisis")
    @Column(name = "dernieresaison")
    private String derniereSaison;

    @Size(min = 10, message = "Indiquer : Disponible, Non disponible ou Série finie")
    @NotBlank(message = "Erreur : que des blancs ont été saisis")
    @Column(name = "prochainesaison")
    private String prochaineSaison;

    @NotNull(message = "Indiquer une provenance.")
    @NotBlank(message = "Erreur : que des blancs ont été saisis")
    private String provenance;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinTable(name = "quivoitquoi", joinColumns = {@JoinColumn(name = "idserie")},
            inverseJoinColumns = {@JoinColumn(name = "idpersonne")})
    @JsonManagedReference
    private List<Personne> lesPersonnes;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDerniereSaison() {
        return this.derniereSaison;
    }

    public void setDerniereSaison(String derniereSaison) {
        this.derniereSaison = derniereSaison;
    }

    public String getProchaineSaison() {
        return this.prochaineSaison;
    }

    public void setProchaineSaison(String prochaineSaison) {
        this.prochaineSaison = prochaineSaison;
    }

    public String getProvenance() {
        return this.provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public List<Personne> getLesPersonnes() {
        return this.lesPersonnes;
    }

    public void setLesPersonnes(List<Personne> lesPersonnes) {
        this.lesPersonnes = lesPersonnes;
    }

    public Serie() {
    }

    public Serie(@NotNull String nom, String derniereSaison, String prochaineSaison, String provenance) {
        this.nom = nom;
        this.derniereSaison = derniereSaison;
        this.prochaineSaison = prochaineSaison;
        this.provenance = provenance;
    }

    public String toString() {
        return "Series{id=" + this.id + ", nom='" + this.nom + '\'' + ", derniereSaison='" + this.derniereSaison + '\'' + ", prochaineSaison='" + this.prochaineSaison + '\'' + ", provenance='" + this.provenance + '\'' + '}';
    }
}

