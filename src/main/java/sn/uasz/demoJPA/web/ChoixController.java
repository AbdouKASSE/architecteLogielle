package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Choix;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Maquette;
import sn.uasz.demoJPA.repostory.ChoixRepository;

import java.util.List;

@Controller
public class ChoixController {
    @Autowired
    ChoixRepository choixRepository;

    @GetMapping("/indexChoix")
    public String index(Model model){
        List<Choix> enseignants = choixRepository.findAll();
        model.addAttribute("listeChoix",enseignants);
        return "choixes";
    }
    @RequestMapping("/editChoix")
    public String editChoix(Model model,@RequestParam(name= "id") Long id){
        Choix enseignant = choixRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Choix introuvable");
        model.addAttribute("choix",enseignant);
        return  "editChoix";
    }
    @GetMapping("/deleteChoix")
    public String deleteChoix (@RequestParam(name = "id") Long id){
        choixRepository.deleteById(id);
        return "redirect:/indexChoix";
    }
    @PostMapping(path = "/saveChoix")
    public String save( Choix enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        choixRepository.save(enseignant);
        return "redirect:/indexChoix";
    }
    @RequestMapping(value = "/formChoix",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("choix",new Enseignant());
        return "addChoix";
    }
}
