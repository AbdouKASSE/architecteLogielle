package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.repostory.EtudiantRepository;

import java.util.List;

@Controller
public class EtudiantContoller {
    @Autowired
    EtudiantRepository etudiantRepository;

    @GetMapping("/etudiant/user/indexEtudiant")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Etudiant> pageEnseignants=etudiantRepository.findByPrenomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEtudiants",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/etudiant/etudiants";

    }
    @RequestMapping("/etudiant/admin/editEtudiant")
    public String editEtudiant (Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Etudiant enseignant = etudiantRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Etudiant introuvable");
        model.addAttribute("etudiant",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/etudiant/editformulaireEtu";
    }
    @GetMapping("/etudiant/admin/deleteEtudiant")
    public String deleteEtudiant (@RequestParam(name = "id") Long id,String keyword,int page){
        etudiantRepository.deleteById(id);
        return "redirect:/etudiant/user/indexEtudiant?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/etudiant/admin/saveEtudiant")
    public String save(@Valid Etudiant enseignant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) return "/etudiant/addformulaireEtu";
        etudiantRepository.save(enseignant);
        return "redirect:/etudiant/user/indexEtudiant?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/etudiant/admin/formEtudiant",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("etudiant",new Etudiant());
        return "/etudiant/addformulaireEtu";
    }
}
