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
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Groupe;
import sn.uasz.demoJPA.repostory.ClasseRepository;
import sn.uasz.demoJPA.repostory.GroupeRepository;

import java.util.List;

@Controller
public class GroupeController {
    @Autowired
    GroupeRepository groupeRepository;
    @Autowired
    ClasseRepository classeRepository;

    @GetMapping("/groupe/user/indexGroupe")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Groupe> pageEnseignants=groupeRepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeGroupes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/groupe/groupes";

    }


    @GetMapping("/groupe/user/indexGroupe2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") Long keyword){
        Page<Groupe> pageEnseignants=groupeRepository.findByClasse_id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeGroupes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/groupe/groupes2";

    }



    @RequestMapping("/groupe/admin/editGroupe")
    public String editGroupe(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Groupe enseignant = groupeRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Groupe introuvable");
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("classe1",classes);
        model.addAttribute("groupe",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/groupe/editGroupe";
    }
    @GetMapping("/groupe/admin/deleteGroupe")
    public String deleteGroupe (@RequestParam(name = "id") Long id,String keyword,int page){
        groupeRepository.deleteById(id);
        return "redirect:/groupe/user/indexGroupe?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/groupe/admin/saveGroupe")
    public String save(@Valid Groupe enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Classe> classes = classeRepository.findAll();
            model.addAttribute("classe1",classes);
            return "/groupe/addGroupe";
        }
        groupeRepository.save(enseignant);
        return "redirect:/groupe/user/indexGroupe?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/groupe/admin/formGroupe",method = RequestMethod.GET)
    public String form (Model model){
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("classe1",classes);
        model.addAttribute("groupe",new Groupe());
        return "/groupe/addGroupe";
    }
}
