package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.repostory.EnseignantRepostory;
import sn.uasz.demoJPA.service.EnseignantService;

import java.awt.print.Pageable;
import java.util.List;

@Controller
//@RequestMapping("/api/users")
public class EnseignantController {
    @Autowired
    EnseignantRepostory enseignantRepostory;
   /* @Autowired
    EnseignantService enseignantService;
    /*@PostMapping("/ajouter")
    public Enseignant addEnsignant(@RequestBody Enseignant enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        return enseignantService.addEnseignant(enseignant);
    }
    @PostMapping("users/ajouter")
    public String addEnsei(@ModelAttribute Enseignant enseignant, Model model) {
        // Appeler le service pour enregistrer l'utilisateur
        enseignantService.addEnseignant(enseignant);

        // Ajouter un message de confirmation
        model.addAttribute("message", "Utilisateur ajouté avec succès !");
        return "confirmation"; // Rediriger vers une page de confirmation
    }

    @GetMapping("users/formulaire")
    public String afficherFormulaire() {
        return "formulaire"; // Nom du fichier HTML pour le formulaire
    }

    @GetMapping("users/liste")
    public List<Enseignant> getAllEnseignant() {
        // Retourner la liste des utilisateurs
        return enseignantService.getAllEnseignant();
    }
    @GetMapping("users/{id}")
    public Enseignant obtenirUtilisateurParId(@PathVariable Long id) {
        return enseignantService.getById(id);
    }*/
    @GetMapping("/index")
    public String index(Model model ,
                        @RequestParam(name = "pade",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Enseignant> pageEnseignants=enseignantRepostory.findByPrenomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEnseignants",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "enseignants";

    }
    @RequestMapping("/editEnseignant")
    public String editEnseignant(Model model,@RequestParam(name= "id") Long id){
        Enseignant enseignant = enseignantRepostory.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignant introuvable");
        model.addAttribute("enseignant",enseignant);
        return  "editFormulaire";
    }
    @GetMapping("/deleteEnseignant")
    public String deleteEnseignant (@RequestParam(name = "id") Long id){
        enseignantRepostory.deleteById(id);
        return "redirect:/index";
    }
    @PostMapping(path = "/save")
    public String save( Enseignant enseignant) {
        // Appeler le service pour enregistrer l'utilisateur
        enseignantRepostory.save(enseignant);
        return "redirect:/index";
    }
    @RequestMapping(value = "/form",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("enseignant",new Enseignant());
        return "addformulaire";
    }



    }
