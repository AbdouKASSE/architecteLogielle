package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.repostory.EnseignementRepository;

import java.util.List;

@Controller
public class EnseignementController {
    @Autowired
    EnseignementRepository enseignementRepository;

    @GetMapping("/indexEnseignement")
    public String index(Model model){
        List<Enseignement> enseignants = enseignementRepository.findAll();
        model.addAttribute("listeEnseignaements",enseignants);
        return "enseignements";
    }
    @RequestMapping("/editEnseignement")
    public String editEnseignement(Model model,@RequestParam(name= "id") Long id){
        Enseignement enseignant = enseignementRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignement introuvable");
        model.addAttribute("enseignement",enseignant);
        return  "editEnseignement";
    }
    @GetMapping("/deleteEnseignement")
    public String deleteEnseignement (@RequestParam(name = "id") Long id){
        enseignementRepository.deleteById(id);
        return "redirect:/indexEnseignement";
    }
    @PostMapping(path = "/saveEnseignement")
    public String save( Enseignement enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        enseignementRepository.save(enseignant);
        return "redirect:/indexEnseignement";
    }
    @RequestMapping(value = "/formEnseignement",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("enseignement",new Enseignant());
        return "addEnseignement";
    }
}
