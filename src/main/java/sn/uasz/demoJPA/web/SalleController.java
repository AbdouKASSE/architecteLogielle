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
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Salle;
import sn.uasz.demoJPA.repostory.SalleRepository;

import java.util.List;

@Controller
public class SalleController {
    @Autowired
    SalleRepository salleRepository;

    @GetMapping("/salle/user/indexSalle")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Salle> pageEnseignants=salleRepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeSalles",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/salle/salles";

    }
    @RequestMapping("/salle/admin/editSalle")
    public String editsalle(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Salle enseignant = salleRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Salle introuvable");
        model.addAttribute("salle",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/salle/editSalle";
    }
    @GetMapping("/salle/admin/deleteSalle")
    public String deleteSalle (@RequestParam(name = "id") Long id,String keyword,int page){
        salleRepository.deleteById(id);
        return "redirect:/salle/user/indexSalle?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/salle/admin/saveSalle")
    public String save(@Valid Salle enseignant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) return "/salle/addSalle";
        salleRepository.save(enseignant);
        return "redirect:/salle/user/indexSalle?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/salle/admin/formSalle",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("salle",new Salle());
        return "salle/addSalle";
    }
}
