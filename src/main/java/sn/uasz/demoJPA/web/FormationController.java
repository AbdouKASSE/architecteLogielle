package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Classe;
import sn.uasz.demoJPA.entiries.Formation;
import sn.uasz.demoJPA.entiries.Groupe;
import sn.uasz.demoJPA.repostory.Formationrepository;

import java.util.List;

@Controller
public class FormationController {
    @Autowired
    Formationrepository formationrepository;
    @GetMapping("/formation/user/indexFormation")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Formation> pageEnseignants=formationrepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeFormations",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "formation/formations";

    }




    @RequestMapping("/formation/admin/editFormation")
    public String editGroupe(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Formation enseignant = formationrepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Formation introuvable");
        model.addAttribute("formation",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/formation/editFormation";
    }
    @GetMapping("/formation/admin/deleteFormation")
    public String deleteGroupe (@RequestParam(name = "id") Long id,String keyword,int page){
        formationrepository.deleteById(id);
        return "redirect:/formation/user/indexFormation?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/formation/admin/saveFormation")
    public String save(@Valid Formation enseignant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) return "/formation/addFormation";
        formationrepository.save(enseignant);
        return "redirect:/formation/user/indexFormation?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/formation/admin/formFormation",method = RequestMethod.GET)
    public String form (Model model,
                        @RequestParam(defaultValue = "")String keyword
    ){
        model.addAttribute("formation",new Formation());
        model.addAttribute("keyword",keyword);
        return "formation/addFormation";
    }

}
