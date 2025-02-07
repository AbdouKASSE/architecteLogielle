package sn.uasz.demoJPA.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sn.uasz.demoJPA.entiries.EC;
import sn.uasz.demoJPA.entiries.UE;
import sn.uasz.demoJPA.entiries.Userss;
import sn.uasz.demoJPA.repostory.UsersRepository;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UsersRepository usersRepository;

    @RequestMapping(value = "/indexPP",method = RequestMethod.GET)
    public String form (Model model, Long id,
                        @RequestParam(defaultValue = "")String keyword){
        model.addAttribute("users",new Userss());
        model.addAttribute("keyword",keyword);
        return "/pro";
    }

}
