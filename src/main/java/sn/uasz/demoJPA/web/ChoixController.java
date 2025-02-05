package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Choix;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Maquette;
import sn.uasz.demoJPA.repostory.ChoixRepository;
import sn.uasz.demoJPA.repostory.EnseignantRepostory;
import sn.uasz.demoJPA.repostory.EnseignementRepository;

import java.util.List;

@Controller
public class ChoixController {
    @Autowired
    ChoixRepository choixRepository;
    @Autowired
    EnseignantRepostory enseignantRepostory;
    @Autowired
    EnseignementRepository enseignementRepository;


    @GetMapping("choix/user/indexChoix")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Choix> pageEnseignants=choixRepository.findByDateContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeChoixes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "choix/choixes";

    }

    @GetMapping("choix/user/indexChoix2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "0") Long keyword){
        Page<Choix> pageEnseignants=choixRepository.findByEnseignants_id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeChoixes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "choix/choixes2";

    }

    @RequestMapping("/choix/user/editChoix")
    public String editChoix(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Choix enseignant = choixRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Choix introuvable");
        model.addAttribute("choix",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/choix/editChoix";
    }
    @GetMapping("/choix/user/deleteChoix")
    public String deleteChoix (@RequestParam(name = "id") Long id,String keyword,int page){
        choixRepository.deleteById(id);
        return "redirect:/choix/user/indexChoix?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/choix/user/saveChoix")
    public String saveChoix(@Valid Choix enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "0")Long keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Enseignant> enseignants = enseignantRepostory.findAll();
            List<Enseignement> enseignements = enseignementRepository.findAll();
            model.addAttribute("enseignant",enseignants);
            model.addAttribute("enseignement",enseignements);
            return "/choix/addChoix";
        }
        choixRepository.save(enseignant);
        return "redirect:/choix/user/indexChoix?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/choix/user/formChoix",method = RequestMethod.GET)
    public String formChoix (Model model,
                             @RequestParam(defaultValue = "")String keyword
                             ){
        List<Enseignant> enseignants = enseignantRepostory.findAll();
        List<Enseignement> enseignements = enseignementRepository.findAll();
        model.addAttribute("enseignant",enseignants);
        model.addAttribute("enseignement",enseignements);
        model.addAttribute("choix",new Choix());
        model.addAttribute("keyword",keyword);
        return "choix/addChoix";
    }

}
