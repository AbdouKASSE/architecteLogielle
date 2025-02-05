package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sn.uasz.demoJPA.entiries.Userss;
import sn.uasz.demoJPA.repostory.UsersRepository;
@Controller
public class ProfileController {
    @Autowired
    UsersRepository usersRepository;
    @GetMapping("/index1")
    public String index(Model model ,
                        @RequestParam(name = "page",defaultValue = "0") int page ,
                        @RequestParam(name = "size",defaultValue = "5") int size,
                        @RequestParam(name = "keyword",defaultValue = "1") Long keyword){
        Page<Userss> pageEnseignants=usersRepository.findById(keyword, PageRequest.of(page,size));
        model.addAttribute("user",pageEnseignants.getContent());
        model.addAttribute("pages",new int[pageEnseignants.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("keyword",keyword);
        return "pro";

    }

}
