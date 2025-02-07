package sn.uasz.demoJPA.security;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private PasswordEncoder passwordEncoder;
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
    public String save(@RequestParam String username,
                       @RequestParam String password,
                       @RequestParam String passwdC) {
        System.out.println("Tentative d'enregistrement pour l'utilisateur : " + username);
        if (!password.equals(passwdC)) {
            return "/inscrire"; // Retourne à la page d'inscription
        }

        // Vérifiez si l'utilisateur existe déjà
        if (userDetailsManager.userExists(username)) {
            System.out.println("L'utilisateur existe déjà : " + username);
            return "/inscrire"; // L'utilisateur existe déjà
        }

        // Créez l'utilisateur dans la base de données
        try {
            userDetailsManager.createUser(
                    User.withUsername(username)
                            .password(passwordEncoder.encode(password))
                            .roles("USER")
                            .build()
            );
            System.out.println("Utilisateur enregistré avec succès : " + username);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'enregistrement : " + e.getMessage());
            return "/inscrire";
        }
        // Redirigez vers la page de connexion
        return "redirect:/login";
    }
}
