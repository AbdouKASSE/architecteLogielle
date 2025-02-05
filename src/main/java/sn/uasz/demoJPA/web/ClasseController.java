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
import sn.uasz.demoJPA.entiries.Formation;
import sn.uasz.demoJPA.entiries.UE;
import sn.uasz.demoJPA.repostory.ClasseRepository;
import sn.uasz.demoJPA.repostory.Formationrepository;

import java.util.List;

@Controller
public class ClasseController {
    @Autowired
    private ClasseRepository classeRepository;
    @Autowired
    Formationrepository formationrepository;

    @GetMapping("/classe/user/indexClasse")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Classe> pageEnseignants=classeRepository.findByNomContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeClasses",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/classe/classes";

    }

    @GetMapping("/classe/user/indexClasse2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") Long keyword){
        Page<Classe> pageEnseignants=classeRepository.findByClasseFr_Id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeClasses",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/classe/classes2";

    }


    @RequestMapping("/classe/admin/editClasse")
    public String editEnseignant(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Classe enseignant = classeRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Classe introuvable");
        List<Formation>formations = formationrepository.findAll();
        model.addAttribute("formation",formations);
        model.addAttribute("classe",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/classe/editClasse";
    }
    @GetMapping("/classe/admin/deleteClasse")
    public String deleteEnseignant (@RequestParam(name = "id") Long id,String keyword,int page){
        classeRepository.deleteById(id);
        return "redirect:/classe/user/indexClasse?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/classe/admin/saveClasse")
    public String save(@Valid Classe enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Formation> ecs = formationrepository.findAll();
            model.addAttribute("formation",ecs);
            return "/classe/addClasse";
        }
        classeRepository.save(enseignant);
        return "redirect:/classe/user/indexClasse?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/classe/admin/formClasse",method = RequestMethod.GET)
    public String form (Model model){
        List<Formation>formations = formationrepository.findAll();
        model.addAttribute("formation",formations);
        model.addAttribute("classe",new Classe());
        return "/classe/addClasse";
    }
}
