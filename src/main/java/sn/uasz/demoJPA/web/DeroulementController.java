package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Deroulement;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.repostory.DeroulementRepository;

import java.util.List;

@Controller
public class DeroulementController {
    @Autowired
    DeroulementRepository deroulementRepository;
    @GetMapping("/indexDeroulement")
    public String index(Model model){
        List<Deroulement> enseignants = deroulementRepository.findAll();
        model.addAttribute("listeDeroulements",enseignants);
        return "deroulements";
    }
    @RequestMapping("/editDeroulement")
    public String editEnseignement(Model model,@RequestParam(name= "id") Long id){
        Deroulement enseignant = deroulementRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Deroulement introuvable");
        model.addAttribute("deroulement",enseignant);
        return  "editDeroulement";
    }
    @GetMapping("/deleteDeroulement")
    public String deleteEnseignement (@RequestParam(name = "id") Long id){
        deroulementRepository.deleteById(id);
        return "redirect:/indexDeroulement";
    }
    @PostMapping(path = "/saveDeroulement")
    public String save( Deroulement enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        deroulementRepository.save(enseignant);
        return "redirect:/indexDeroulement";
    }
    @RequestMapping(value = "/formDeroulement",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("deroulement",new Enseignant());
        return "addDeroulement";
    }
}
