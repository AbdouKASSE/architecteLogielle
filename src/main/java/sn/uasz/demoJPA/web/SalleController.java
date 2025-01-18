package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Salle;
import sn.uasz.demoJPA.repostory.SalleRepository;

import java.util.List;

@Controller
public class SalleController {
    @Autowired
    SalleRepository salleRepository;
    @GetMapping("/indexSalle")
    public String index(Model model){
        List<Salle> enseignants = salleRepository.findAll();
        model.addAttribute("listeSalles",enseignants);
        return "salles";
    }
    @RequestMapping("/editSalle")
    public String editSalle(Model model,@RequestParam(name= "id") Long id){
        Salle enseignant = salleRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("salle introuvable");
        model.addAttribute("salle",enseignant);
        return  "editSalle";
    }
    @GetMapping("/deleteSalle")
    public String deleteSalle (@RequestParam(name = "id") Long id){
        salleRepository.deleteById(id);
        return "redirect:/indexSalle";
    }
    @PostMapping(path = "/saveSalle")
    public String save( Salle enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        salleRepository.save(enseignant);
        return "redirect:/indexSalle";
    }
    @RequestMapping(value = "/formSalle",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("salle",new Enseignant());
        return "addSalle";
    }
}
