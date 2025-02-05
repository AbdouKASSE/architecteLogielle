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
import sn.uasz.demoJPA.repostory.EcRepository;
import sn.uasz.demoJPA.repostory.UeRepository;

import java.util.List;

@Controller
public class EcController {
    @Autowired
    EcRepository ecRepository;
    @Autowired
    UeRepository ueRepository;

    @GetMapping("/ec/user/indexEc")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<EC> pageEnseignants=ecRepository.findByLibelleContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEcs",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/ec/ecs";

    }

    @GetMapping("/ec/user/indexEc2")
    public String index2(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") Long keyword){
        Page<EC> pageEnseignants=ecRepository.findByUeEC_id(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEcs",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/ec/ecs2";

    }

    @RequestMapping("/ec/admin/editEc")
    public String editEc(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        EC enseignant = ecRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("Ec introuvable");
        model.addAttribute("ec",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/ec/editformulaireEc";
    }
    @GetMapping("/ec/admin/deleteEc")
    public String deleteEc (@RequestParam(name = "id") Long id,String keyword,int page){
        ecRepository.deleteById(id);
        return "redirect:/ec/user/indexEc?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/ec/admin/saveEc")
    public String save(@Valid EC enseignant, BindingResult bindingResult,Model model,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()){
            List<UE> ecs = ueRepository.findAll();
            model.addAttribute("ec",new EC());
            model.addAttribute("ues",ecs);
            return "/ec/addformulaireEc";
        }
        ecRepository.save(enseignant);
        return "redirect:/ec/user/indexEc?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/ec/admin/formEc",method = RequestMethod.GET)
    public String form (Model model,
                        @RequestParam(defaultValue = "")String keyword){
        List<UE> enseignants =ueRepository.findAll();
        model.addAttribute("ue",enseignants);
        model.addAttribute("ec",new EC());
        model.addAttribute("keyword",keyword);
        return "ec/addformulaireEc";
    }
}
