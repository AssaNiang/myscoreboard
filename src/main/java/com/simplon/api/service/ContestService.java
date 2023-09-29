package com.simplon.api.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simplon.api.model.Contest;
import com.simplon.api.repository.ContestRepository;

import lombok.Data;

@Data
@Service
public class ContestService {
    @Autowired /*evite de creer un constructeur en dur */
    private ContestRepository gameRepository;
    /*
     * Récupérer tous les jeux
     */
    public Iterable<Contest>getAllContests(){
       return gameRepository.findAll(); 
    }
    /*
     * Récupérer un jeu par son id
     */
    //Optinal peut retourné un jeu ou null et final =id ne peut pas etre modifier
    public Optional<Contest>getContest(final long id){
        return gameRepository.findById(id);
    }
    /*Ajouter/modifier un jeu*/
    public Contest saveContest(Contest g){
        return this.gameRepository.save(g);
    }
    /*supprimer un jeu */
    public void deleteContest(final long id){
        this.gameRepository.deleteById(id);
    }
}