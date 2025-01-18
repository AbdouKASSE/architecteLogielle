package sn.uasz.demoJPA.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.UE;
import sn.uasz.demoJPA.repostory.UeRepository;

import java.util.List;

@Controller
public class UeController {
    @Autowired
    UeRepository ueRepository;

    @GetMapping("/indexUe")
    public String index(Model model){
        List<UE> enseignants = ueRepository.findAll();
        model.addAttribute("listeUes",enseignants);
        return "ues";
    }
    @RequestMapping("/editUe")
    public String editUe(Model model,@RequestParam(name= "id") Long id){
        UE enseignant = ueRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("UE introuvable");
        model.addAttribute("ue",enseignant);
        return "editUe";
    }
    @GetMapping("/deleteUe")
    public String deleteUe (@RequestParam(name = "id") Long id){
        ueRepository.deleteById(id);
        return "redirect:/indexUe";
    }
    @PostMapping(path = "/saveUe")
    public String save( UE enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        ueRepository.save(enseignant);
        return "redirect:/indexUe";
    }
    @RequestMapping(value = "/formUes",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("ues",new UE());
        return "addUes";
    }

}
