package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.*;
import sn.uasz.demoJPA.repostory.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class SeanceController {
    @Autowired
    SeanceRepository seanceRepository;
    @Autowired
    SalleRepository salleRepository;
    @Autowired
    EnseignementRepository enseignementRepository;
    @Autowired
    EmploisDuTpRepository emploisDuTpRepository;
    @Autowired
    ChoixRepository choixRepository;


    @GetMapping("/seance/user/indexSeance")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword) {
        Page<Seance> pageEnseignants = seanceRepository.findAllByCodeContainsIgnoreCase(keyword, PageRequest.of(page, size));
        model.addAttribute("listeSeances", pageEnseignants.getContent());
        model.addAttribute("pages", new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "/seance/seances";
    }

    @GetMapping("/seance/user/indexSeance2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "0") Long keyword) {
        Page<Seance> pageEnseignants = seanceRepository.findBySeanceETP_id(keyword, PageRequest.of(page, size));
        model.addAttribute("listeSeances", pageEnseignants.getContent());
        model.addAttribute("pages", new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "/seance/seance2";
    }

    @RequestMapping("/seance/admin/editSeance")
    public String editMaquette(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Seance enseignant = seanceRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Seance introuvable");
        model.addAttribute("seance",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/seance/editSeance";
    }
    @GetMapping("/seance/admin/deleteSeance")
    public String deleteMaquette (@RequestParam(name = "id") Long id,String keyword,int page){
        seanceRepository.deleteById(id);
        return "redirect:/seance/user/indexSeance?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/seance/admin/saveSeance")
    public String save(@Valid Seance enseignant, BindingResult bindingResult, Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Salle> classes = salleRepository.findAll();
            List<Enseignement> enseignements = enseignementRepository.findAll();
            List<EmploisDuTp> emploisDuTps = emploisDuTpRepository.findAll();
            model.addAttribute("salle",classes);
            model.addAttribute("enseign",enseignements);
            model.addAttribute("empl",emploisDuTps);
            return "/seance/addSeance";
        }
        seanceRepository.save(enseignant);
        return "redirect:/seance/user/indexSeance?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/seance/admin/formSeance",method = RequestMethod.GET)
    public String form (Model model){
        List<Salle> classes = salleRepository.findAll();
        List<Enseignement> enseignements = enseignementRepository.findAll();
        List<EmploisDuTp> emploisDuTps = emploisDuTpRepository.findAll();
        model.addAttribute("salle",classes);
        model.addAttribute("enseign",enseignements);
        model.addAttribute("empl",emploisDuTps);
        model.addAttribute("seance",new Seance());
        return "/seance/addSeance";
    }

    @GetMapping("/seance/user/affiche")
    public String afficher(Model model) {
        // Tableau à deux dimensions
        EmploisDuTp emploisDuTp = emploisDuTpRepository.findById(1L).orElse(null);
        Seance seance1 = seanceRepository.findById(1L).orElse(null);
        List<List<Seance>>tableau = new ArrayList<>();
        List<Seance> seances = seanceRepository.findAll();
        List<Choix> choixes = new ArrayList<>();
        for (Seance seance : seances) {
            Long id = seance.getSeanceENG().getId();
            Choix choix = choixRepository.findById(id).orElse(null);
            choixes.add(choix);
            tableau.add(Arrays.asList(
                new Seance(seance.getId(),seance.getCode(),seance.getHeureDeb(),seance.getHeureFin(),seance.getSeanceENG(),seance.getSeanceSA(),seance.getSeanceETP(),null)
            ));
        }
        model.addAttribute("tableau", tableau);
        model.addAttribute("emplois", emploisDuTp);
        model.addAttribute("seance", seance1);
        return "/seance/afficher";
    }

}
