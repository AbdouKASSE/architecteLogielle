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
import sn.uasz.demoJPA.entiries.Maquette;
import sn.uasz.demoJPA.repostory.ClasseRepository;
import sn.uasz.demoJPA.repostory.MaquetteRepository;

import java.util.List;

@Controller
public class MaquetteController {
    @Autowired
    MaquetteRepository maquetteRepository;
    @Autowired
    ClasseRepository classeRepository;

    @GetMapping("/maquette/user/indexMaquette")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<Maquette> pageEnseignants=maquetteRepository.findByCodeContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeMaquettes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/maquette/maquettes";

    }
    @RequestMapping("/maquette/admin/editMaquette")
    public String editMaquette(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        Maquette enseignant = maquetteRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Maquette introuvable");
        model.addAttribute("maquette",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/maquette/editMaquette";
    }
    @GetMapping("/maquette/admin/deleteMaquette")
    public String deleteMaquette (@RequestParam(name = "id") Long id,String keyword,int page){
        maquetteRepository.deleteById(id);
        return "redirect:/maquette/user/indexMaquette?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/maquette/admin/saveMaquette")
    public String save(@Valid Maquette enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Classe> classes = classeRepository.findAll();
            model.addAttribute("classe1",classes);
            return "/maquette/addMaquette";
        }
        maquetteRepository.save(enseignant);
        return "redirect:/maquette/user/indexMaquette?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/maquette/admin/formMaquette",method = RequestMethod.GET)
    public String form (Model model){
        List<Classe> classes = classeRepository.findAll();
        model.addAttribute("classe1",classes);
        model.addAttribute("maquette",new Maquette());
        return "/maquette/addMaquette";
    }

}
