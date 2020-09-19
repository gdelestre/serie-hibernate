package fr.springboot.serie_hibernate.controller;


import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import fr.springboot.serie_hibernate.entity.Personne;
import fr.springboot.serie_hibernate.entity.Serie;
import fr.springboot.serie_hibernate.service.PersonneService;
import fr.springboot.serie_hibernate.service.SerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PrincipalController {
    @Autowired
    private PersonneService personneService;
    @Autowired
    private SerieService serieService;
    private int idVisionneur;

    public PrincipalController(PersonneService personneService, SerieService serieService) {
        this.personneService = personneService;
        this.serieService = serieService;
    }

    @RequestMapping({"/"})
    public String home() {
        return "redirect:/list";
    }

    @GetMapping({"/list"})
    public String ListSeriePersonne(Model model) {
        List<Personne> lesPersonnes = this.personneService.findAll();
        model.addAttribute("personnes", lesPersonnes);
        List<Serie> lesSeriesDuo = this.serieService.findDuo();
        model.addAttribute("seriesDuo", lesSeriesDuo);
        List<Serie> lesSeriesSolo = this.serieService.findSolo();
        model.addAttribute("seriesSolo", lesSeriesSolo);
        return "liste-series";
    }

    @GetMapping({"/updateSerie"})
    public String showFormForUpdate(@RequestParam("serieId") int theId, Model theModel) {
        Serie laSerie = this.serieService.findById(theId);
        theModel.addAttribute("serie", laSerie);
        return "serie-form";
    }

    @GetMapping({"/addSerie"})
    public String showFormForAdd(@RequestParam("personneId") int theId, Model leModel) {
        this.idVisionneur = theId;
        Serie nouvelleSerie = new Serie();
        leModel.addAttribute("serie", nouvelleSerie);
        return "serie-form";
    }

    @PostMapping({"/saveOrUpdate"})
    public String saveOrUpdate(@Valid @ModelAttribute("serie") Serie laSerie, BindingResult theBindingResult) {
        if (theBindingResult.hasErrors()) {
            return "serie-form";
        } else {
            if (laSerie.getId() != 0) {
                Serie serieOrigine = this.serieService.findById(laSerie.getId());
                if (serieOrigine.getNom() != laSerie.getNom()) {
                    serieOrigine.setNom(laSerie.getNom());
                }

                if (serieOrigine.getProvenance() != laSerie.getProvenance()) {
                    serieOrigine.setProvenance(laSerie.getProvenance());
                }

                if (serieOrigine.getDerniereSaison() != laSerie.getDerniereSaison()) {
                    serieOrigine.setDerniereSaison(laSerie.getDerniereSaison());
                }

                if (serieOrigine.getProchaineSaison() != laSerie.getProchaineSaison()) {
                    serieOrigine.setProchaineSaison(laSerie.getProchaineSaison());
                }

                this.serieService.saveOrUpdate(serieOrigine);
            } else {
                List<Personne> lesVisionneurs = new ArrayList();
                if (this.idVisionneur == 0) {
                    lesVisionneurs = this.personneService.findAll();
                } else {
                    Personne visionneur = this.personneService.findById(this.idVisionneur);
                    ((List) lesVisionneurs).add(visionneur);
                }

                laSerie.setLesPersonnes((List) lesVisionneurs);
                this.serieService.saveOrUpdate(laSerie);
            }

            return "redirect:/list";
        }
    }

    @GetMapping({"/deleteSerie"})
    public String deleteSerie(@RequestParam("serieId") int theId) {
        this.serieService.deleteById(theId);
        return "redirect:/list";
    }
}

