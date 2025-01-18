package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Maquette;
import sn.uasz.demoJPA.repostory.MaquetteRepository;

import java.util.List;

@Controller
public class MaquetteController {
    @Autowired
    MaquetteRepository maquetteRepository;

    @GetMapping("/indexMaquette")
    public String index(Model model){
        List<Maquette> enseignants = maquetteRepository.findAll();
        model.addAttribute("listeMaquettes",enseignants);
        return "maquettes";
    }
    @RequestMapping("/editMaquette")
    public String editMaquette(Model model,@RequestParam(name= "id") Long id){
        Maquette enseignant = maquetteRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Maquette introuvable");
        model.addAttribute("maquette",enseignant);
        return  "editMaquette";
    }
    @GetMapping("/deleteMaquette")
    public String deleteMaquette (@RequestParam(name = "id") Long id){
        maquetteRepository.deleteById(id);
        return "redirect:/indexMaquette";
    }
    @PostMapping(path = "/saveMaquette")
    public String save( Maquette enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        maquetteRepository.save(enseignant);
        return "redirect:/indexMaquette";
    }
    @RequestMapping(value = "/formMaquette",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("maquette",new Enseignant());
        return "addMaquette";
    }

}
