package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Deroulement;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Enseignement;
import sn.uasz.demoJPA.entiries.Seance;
import sn.uasz.demoJPA.repostory.DeroulementRepository;
import sn.uasz.demoJPA.repostory.SeanceRepository;

import java.util.List;

@Controller
public class DeroulementController {
    @Autowired
    DeroulementRepository deroulementRepository;
    @Autowired
    SeanceRepository seanceRepository;

    @GetMapping("/deroulement/user/indexDeroulement")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Deroulement> pageEnseignants=deroulementRepository.findByHeureDebContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeDeroulements",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/deroulement/deroulements";

    }
    @RequestMapping("/deroulement/admin/editDeroulement")
    public String editDeroulement(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Deroulement enseignant = deroulementRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Deroulement introuvable");
        List<Seance> seances = seanceRepository.findAll();
        model.addAttribute("seance",seances);
        model.addAttribute("deroulement",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/deroulement/editDeroulement";
    }
    @GetMapping("/deroulement/admin/deleteDeroulement")
    public String deleteDeroulement (@RequestParam(name = "id") Long id,String keyword,int page){
        deroulementRepository.deleteById(id);
        return "redirect:/deroulement/user/indexDeroulement?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/deroulement/admin/saveDeroulement")
    public String save(@Valid Deroulement enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) {
            List<Seance> seances = seanceRepository.findAll();
            model.addAttribute("seance",seances);
            return "/deroulement/addDeroulement";
        }
        deroulementRepository.save(enseignant);
        return "redirect:/deroulement/user/indexDeroulement?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/deroulement/admin/formDeroulement",method = RequestMethod.GET)
    public String form (Model model){
        List<Seance> seances = seanceRepository.findAll();
        model.addAttribute("seance",seances);
        model.addAttribute("deroulement",new Deroulement());
        return "/deroulement/addDeroulement";
    }
}
