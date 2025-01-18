package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Groupe;
import sn.uasz.demoJPA.repostory.GroupeRepository;

import java.util.List;

@Controller
public class GroupeController {
    @Autowired
    GroupeRepository groupeRepository;
    @GetMapping("/indexGroupe")
    public String index(Model model){
        List<Groupe> enseignants = groupeRepository.findAll();
        model.addAttribute("listeGroupes",enseignants);
        return "groupes";
    }
    @RequestMapping("/editGroupe")
    public String editGroupe(Model model,@RequestParam(name= "id") Long id){
        Groupe enseignant = groupeRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignement introuvable");
        model.addAttribute("groupe",enseignant);
        return  "editGroupe";
    }
    @GetMapping("/deleteGroupe")
    public String deleteGroupe (@RequestParam(name = "id") Long id){
        groupeRepository.deleteById(id);
        return "redirect:/indexEnseignement";
    }
    @PostMapping(path = "/saveGroupe")
    public String save( Groupe enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        groupeRepository.save(enseignant);
        return "redirect:/indexEnseignement";
    }
    @RequestMapping(value = "/formGroupe",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("groupe",new Enseignant());
        return "addGroupe";
    }
}
