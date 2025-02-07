package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;

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
    public String form (Model model, Long id,
                        @RequestParam(defaultValue = "")String keyword){
        model.addAttribute("users",new Userss());
        model.addAttribute("keyword",keyword);
        return "/pro";
    }

}
