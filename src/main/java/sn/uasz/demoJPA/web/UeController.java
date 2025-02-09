package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.entiries.Etudiant;
import sn.uasz.demoJPA.entiries.Maquette;
import sn.uasz.demoJPA.entiries.UE;
import sn.uasz.demoJPA.repostory.MaquetteRepository;
import sn.uasz.demoJPA.repostory.UeRepository;

import java.util.List;

@Controller
public class UeController {
    @Autowired
    UeRepository ueRepository;
    @Autowired
    MaquetteRepository maquetteRepository;

    @GetMapping("/ue/user/indexUe")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<UE> pageEnseignants=ueRepository.findByLibelleContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeUes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/ue/ues";

    }
    @GetMapping("/ue/user/indexUe2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") Long keyword){
        Page<UE> pageEnseignants=ueRepository.findByUeMaquette_id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeUes",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/ue/ues2";

    }
    @RequestMapping("/ue/admin/editUe")
    public String editUe(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        UE enseignant = ueRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Ue introuvable");
        List<Maquette> enseignants =maquetteRepository.findAll();
        model.addAttribute("maquette",enseignants);
        model.addAttribute("ue",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/ue/editUe";
    }
    @GetMapping("/ue/admin/deleteUe")
    public String deleteUe (@RequestParam(name = "id") Long id,String keyword,int page){
        ueRepository.deleteById(id);
        return "redirect:/ue/user/indexUe?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/ue/admin/saveUe")
    public String save(@Valid UE ue, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "") String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<Maquette> enseignants =maquetteRepository.findAll();
            model.addAttribute("maquette",enseignants);
            model.addAttribute("ue", ue);
            return "/ue/addUes";
        }
        ueRepository.save(ue);
        return "redirect:/ue/user/indexUe?page="+page+"&keyword="+ keyword;
    }

    @RequestMapping(value = "/ue/admin/formUe",method = RequestMethod.GET)
    public String form (Model model,
                        @RequestParam(defaultValue = "")String keyword){
        List<Maquette> enseignants =maquetteRepository.findAll();
        model.addAttribute("maquette",enseignants);
        model.addAttribute("ue",new UE());
        model.addAttribute("keyword",keyword);
        return "/ue/addUes";
    }

}
