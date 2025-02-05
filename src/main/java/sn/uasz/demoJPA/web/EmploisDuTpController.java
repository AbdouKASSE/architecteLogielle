package sn.uasz.demoJPA.web;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sn.uasz.demoJPA.entiries.EmploisDuTp;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.repostory.EmploisDuTpRepository;

@Controller
public class EmploisDuTpController {
    @Autowired
    private EmploisDuTpRepository emploisDuTpRepository;
    @GetMapping("/emploisDuTp/user/indexEmploisDuTp")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "") String keyword){
        Page<EmploisDuTp> pageEnseignants=emploisDuTpRepository.findBySemestreContainsIgnoreCase(keyword, PageRequest.of(page,size));
        model.addAttribute("listeEmploisDuTps",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "/emploisDuTp/emploisDuTps";

    }
    @RequestMapping("/emploisDuTp/admin/editEmploisDuTp")
    public String editEmploisDuTp(Model model,@RequestParam(name= "id") Long id,String keyword,int page){
        EmploisDuTp enseignant = emploisDuTpRepository.findById(id).orElse(null);
        if (enseignant==null) throw new RuntimeException("EmploisDuTp introuvable");
        model.addAttribute("emploisDuTp",enseignant);
        model.addAttribute("page",page);
        model.addAttribute("keyword",keyword);
        return  "/emploisDuTp/editEmploisDuTp";
    }
    @GetMapping("/emploisDuTp/admin/deleteEmploisDuTp")
    public String deleteEmploisDuTp (@RequestParam(name = "id") Long id,String keyword,int page){
        emploisDuTpRepository.deleteById(id);
        return "redirect:/emploisDuTp/user/indexEmploisDuTp?page="+page+"&keyword="+ keyword;
    }
    @PostMapping(path = "/emploisDuTp/admin/saveEmploisDuTp")
    public String save(@Valid EmploisDuTp enseignant, BindingResult bindingResult,
                       @RequestParam(defaultValue = "0")int page,
                       @RequestParam(defaultValue = "")String keyword
    ) {
        // Appeler le service pour enregistrer l'utilisateur
        if (bindingResult.hasErrors()) return "/emploisDuTp/addEmploisDuTp";
        emploisDuTpRepository.save(enseignant);
        return "redirect:/emploisDuTp/user/indexEmploisDuTp?page="+page+"&keyword="+ keyword;
    }
    @RequestMapping(value = "/emploisDuTp/admin/formEmploisDuTp",method = RequestMethod.GET)
    public String form (Model model){
        model.addAttribute("emploisDuTp",new EmploisDuTp());
        return "emploisDuTp/addEmploisDuTp";
    }
}
