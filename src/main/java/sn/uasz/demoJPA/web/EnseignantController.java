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
import sn.uasz.demoJPA.repostory.EnseignantRepostory;
import sn.uasz.demoJPA.service.EnseignantService;

import java.awt.print.Pageable;
import java.util.List;

@Controller
//@RequestMapping("/api/users")
public class EnseignantController {
    @Autowired
    EnseignantRepostory enseignantRepostory;

    @GetMapping("/enseignant/user/index")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Enseignant> pageEnseignants=enseignantRepostory.findByPrenomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEnseignants",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/enseignant/enseignants";

    }
    @RequestMapping("/enseignant/admin/editEnseignant")
    public String editEnseignant(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Enseignant enseignant = enseignantRepostory.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignant introuvable");
        model.addAttribute("enseignant",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/enseignant/editFormulaire";
    }
    @GetMapping(path ="/enseignant/admin/deleteEnseignant")
    public String deleteEnseignant (@RequestParam(name = "id") Long id,String keyword,int page){
        enseignantRepostory.deleteById(id);
        return "redirect:/enseignant/user/index?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/enseignant/admin/save")
    public String save(@Valid Enseignant enseignant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
                       ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) return "/enseignant/addformulaire";
        enseignantRepostory.save(enseignant);
        return "redirect:/enseignant/user/index?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/enseignant/admin/form",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("enseignant",new Enseignant());
        return "/enseignant/addformulaire";
    }

    @GetMapping("/")
    public String home (){
        return "index";
    }



    }
