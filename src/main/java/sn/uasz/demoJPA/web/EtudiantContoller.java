package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.repostory.EtudiantRepository;

import java.util.List;

@Controller
public class EtudiantContoller {
    @Autowired
    EtudiantRepository etudiantRepository;

    @GetMapping("/indexEtudiant")
    public String index(Model model){
        List<Etudiant> enseignants = etudiantRepository.findAll();
        model.addAttribute("listeEtudiants",enseignants);
        return "etudiants";
    }
    @RequestMapping("/editEtudiant")
    public String editEtudiant(Model model,@RequestParam(name= "id") Long id){
        Etudiant enseignant = etudiantRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignant introuvable");
        model.addAttribute("etudiant",enseignant);
        return  "editFormulaireEtu";
    }
    @GetMapping("/deleteEtudiant")
    public String deleteEtudiant (@RequestParam(name = "id") Long id){
        etudiantRepository.deleteById(id);
        return "redirect:/indexEtudiant";
    }
    @PostMapping(path = "/saveEtudiant")
    public String save( Etudiant enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        etudiantRepository.save(enseignant);
        return "redirect:/indexEtudiant";
    }
    @RequestMapping(value = "/formEtu",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "addformulaireEtu";
    }
}
