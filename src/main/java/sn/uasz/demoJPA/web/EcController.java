package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Classe;
import sn.uasz.demoJPA.entiries.EC;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.repostory.EcRepository;

import java.util.List;

@Controller
public class EcController {
    @Autowired
    EcRepository ecRepository;
    @GetMapping("/indexEc")
    public String index(Model model){
        List<EC> enseignants = ecRepository.findAll();
        model.addAttribute("listeEcs",enseignants);
        return "ecs";
    }
    @RequestMapping("/editEc")
    public String editEc(Model model,@RequestParam(name= "id") Long id){
        EC enseignant = ecRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignant introuvable");
        model.addAttribute("ec",enseignant);
        return  "editFormulaireEC";
    }
    @GetMapping("/deleteEc")
    public String deleteEc (@RequestParam(name = "id") Long id){
        ecRepository.deleteById(id);
        return "redirect:/indexEc";
    }
    @PostMapping(path = "/saveEc")
    public String save( EC enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        ecRepository.save(enseignant);
        return "redirect:/indexEc";
    }
    @RequestMapping(value = "/formEc",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("ec",new EC());
        return "addformulaireEc";
    }

}
