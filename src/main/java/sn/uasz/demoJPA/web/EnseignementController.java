package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.EC;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.repostory.EcRepository;
import sn.uasz.demoJPA.repostory.EnseignementRepository;

import java.util.List;

@Controller
public class EnseignementController {
    @Autowired
    EnseignementRepository enseignementRepository;
    @Autowired
    EcRepository ecRepository;

    @GetMapping("/enseignement/user/indexEnseignement")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Enseignement> pageEnseignants=enseignementRepository.findByCodeContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEnseignements",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/enseignement/enseignements";

    }

    @GetMapping("/enseignement/user/indexEnseignement2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") Long keyword){
        Page<Enseignement> pageEnseignants=enseignementRepository.findByEcEnseignement_id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEnseignements",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/enseignement/enseignements2";

    }

    @RequestMapping("/enseignement/admin/editEnseignement")
    public String editEnseignement(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Enseignement enseignant = enseignementRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Enseignement introuvable");
        model.addAttribute("enseignement",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "enseignement/editEnseignement";
    }


    @GetMapping("/enseignement/admin/deleteEnseignement")
    public String deleteEnseignement (@RequestParam(name = "id") Long id,String keyword,int page){
        enseignementRepository.deleteById(id);
        return "redirect:/enseignement/indexEnseignement?page="+page+"&keyword="+ keyword;
    }


    @PostMapping(path = "/enseignement/admin/saveEnseignement")
    public String save(@Valid Enseignement enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<EC> ecs = ecRepository.findAll();
            model.addAttribute("keyword",keyword);
            model.addAttribute("ec",ecs);
            return "/enseignement/addEnseignement";
        }
        enseignementRepository.save(enseignant);
        return "redirect:/enseignement/user/indexEnseignement?page="+page+"&keyword="+ keyword;
    }

    @RequestMapping(value = "/enseignement/admin/formEnseignement",method = RequestMethod.GET)
    public String form (Model model,
                        @RequestParam(defaultValue = "")String keyword
    ){
        List<EC> ec = ecRepository.findAll();
        model.addAttribute("ec",ec);
        model.addAttribute("enseignement",new Enseignement());
        model.addAttribute("keyword",keyword);
        return "enseignement/addEnseignement";
    }
}
