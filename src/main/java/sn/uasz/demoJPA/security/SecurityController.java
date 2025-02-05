package sn.uasz.demoJPA.security;

import jakarta.validation.Valid;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sn.uasz.demoJPA.entiries.EC;

@Controller
public class SecurityController {
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    UserDetailsManager userDetailsManager;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/notAuthorized")
    public String notAuthorized(){
        return "login";
    }
    @GetMapping("/inscrire")
    public String inscrire(Model model){
        model.addAttribute("username","abd");
        model.addAttribute("passwd","1234");
        model.addAttribute("passwdC","1234");
        return "inscrire";
    }

    @PostMapping(path = "/save")
    public String save(String username,String password,String passwdC) {
        PasswordEncoder passwordEncoder = passwordEncoder();
        // Appeler le service pour enregistrer l'utilisateur
        if (!password.equals(passwdC)) return "/inscrire";
        UserDetails u1 = userDetailsManager.loadUserByUsername(username);
        if(u1==null)
            userDetailsManager.createUser(User.withUsername(username).password(passwordEncoder.encode(password)).roles("USER").build()
            );
        return "redirect:/login";
    }

}
