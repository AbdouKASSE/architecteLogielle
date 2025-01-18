package sn.uasz.demoJPA.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sn.uasz.demoJPA.entiries.Enseignant;
import sn.uasz.demoJPA.repostory.EnseignantRepostory;
import sn.uasz.demoJPA.repostory.UsersRepository;

import java.util.List;

@Service
public class EnseignantService {
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    EnseignantRepostory enseignantRepostory;
    public Enseignant addEnseignant(Enseignant enseignant){
        return usersRepository.save(enseignant);

    }

    public List<Enseignant> getAllEnseignant() {
        // Appeler le repository pour récupérer la liste des utilisateurs
        return enseignantRepostory.findAll();
    }

    public Enseignant getById(Long id) {
        // Vérifier si l'utilisateur existe
        return enseignantRepostory.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur avec ID " + id + " introuvable"));
    }
}
