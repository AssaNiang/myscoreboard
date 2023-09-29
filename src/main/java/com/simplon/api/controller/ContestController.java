package com.simplon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.simplon.api.model.Contest;
import com.simplon.api.model.dto.ContestDTO;
import com.simplon.api.service.ContestService;
import com.simplon.api.service.GameService;
import com.simplon.api.service.PlayerService;

@RestController

public class ContestController {
    @Autowired
    private ContestService contestService;
     @Autowired
    private GameService gameService;
     @Autowired
    private PlayerService playerService;


    // si on utilise pas autowired on aura ce code ci dessous a mettre en plus
    // public ContestController(){
    // this.gameService = new ContestService();
    // }
    /*
     * GetMapping: permet de relier cette methode 'allContests' à une url qui sera
     * appelé en methode HTTP GET
     */
    @GetMapping("/contests")
    // public Iterable<Contest> allContests() {
    // return gameService.getAllContests();
    // }
    public Iterable<ContestDTO> allContests() {
        Iterable<Contest> contests = contestService.getAllContests();
        List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
        for (Contest contest :contests) {
            contestsDTO.add(new ContestDTO(contest));
        }
        return contestsDTO;
    }
   

    @GetMapping("/contest/{id}")
    public ContestDTO gameById(@PathVariable("id") long id) {
        Optional<Contest> g = contestService.getContest(id);
        if (g.isPresent()) {
            return new ContestDTO(g.get()) ;// la methode get de l'objet Optinal retourne un objet Contest et on le transforme en ContestDTO en le passant par le constructeur ContestDTO
        } else {
            return null;
        }
    }

    /*
     * L'annotation requestBody Contest est utilisée pour recuperer les donnée passées
     * dans le corps de la requette HTTP
     * en methode HTTP post les données sont passées dans le corps de la requete
     * (alors qu'en get des données peuvent etre passé dans l'url )
     */
    @PostMapping("/contest")
    public ContestDTO insertContest(@RequestParam String start_date,@RequestParam int id_game,@RequestParam Integer id_winner) {
        Contest contest =new Contest();
        contest.setStartDate(Date.valueOf(start_date));
        contest.setGame(gameService.getGame(id_game).get());
        if(id_winner != null){
            contest.setWinner(playerService.getPlayer(id_winner).get());
        }
        return new ContestDTO(contestService.saveContest(contest));
    }

    @DeleteMapping("contest/{id}")
    public boolean deleteContest(@PathVariable("id") long id) {

        /*
         * verifier si un jeu existe pour l'identifiant
         * si le jeu existe:supprimer le game et renvoyer true
         * sinon renvoyer false
         * optinal=on peut retourner qq chose de null
         */
        Optional<Contest> g = contestService.getContest(id);
        if (g.isPresent()) {
            contestService.deleteContest(id);
            return true;
        } else {
            return false;
        }

    }

    @PutMapping("/contest/{id}")
    public Contest updateContest(@PathVariable("id") long id, @RequestBody Contest contest) {
        Optional<Contest> c = contestService.getContest(id);
        if(c.isPresent()) {
            contest.setId(id);
            
            return contestService.saveContest(contest);
        }
        return null;
    }
   
    // @PutMapping("/contest/{id}")
    // public ContestDTO updateContest(@PathVariable("id") final long id,@RequestBody Contest contest){
    //     Optional<Contest> c = contestService.getContest(id);
    //     if(c.isPresent()){
    //         Contest contestToUpdate = c.get();
    //         if(contest.getGame() != null){
    //             contestToUpdate.setGame(contest.getGame());
    //         }
    //         if(contest.getStartDate() != null){
    //             contestToUpdate.setStartDate(contest.getStartDate());
    //         }
    //         if(contest.getWinner() != null){
    //             contestToUpdate.setWinner(contest.getWinner());
    //         }
    //         return new ContestDTO(contestService.saveContest(contest));
    //     }
    //     return  null;
    // }

}