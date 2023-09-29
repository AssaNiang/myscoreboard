package com.simplon.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.simplon.api.model.Game;
import com.simplon.api.model.dto.GameDTO;
import com.simplon.api.service.GameService;

@RestController

public class GameController {
    @Autowired
    private GameService gameService;

    // si on utilise pas autowired on aura ce code ci dessous a mettre en plus
    // public GameController(){
    // this.gameService = new GameService();
    // }
    /*
     * GetMapping: permet de relier cette methode 'allGames' à une url qui sera
     * appelé en methode HTTP GET
     */
    @GetMapping("/games")
    // public Iterable<Game> allGames() {
    // return gameService.getAllGames();
    // }
    public Iterable<GameDTO> allGames() {
        Iterable<Game> games = gameService.getAllGames();
        List<GameDTO> gamesDTO = new ArrayList<GameDTO>();
        for (Game game : games) {
            gamesDTO.add(new GameDTO(game));
        }
        return gamesDTO;
    }

    @GetMapping("/game/{id}")
    public GameDTO gameById(@PathVariable("id") long id) {
        Optional<Game> g = gameService.getGame(id);
        if (g.isPresent()) {
            return new GameDTO(g.get()) ;// la methode get de l'objet Optinal retourne un objet Game et on le transforme en GameDTO en le passant par le constructeur GameDTO
        } else {
            return null;
        }
    }

    /*
     * L'annotation requestBody Game est utilisée pour recuperer les donnée passées
     * dans le corps de la requette HTTP
     * en methode HTTP post les données sont passées dans le corps de la requete
     * (alors qu'en get des données peuvent etre passé dans l'url )
     */
    @PostMapping("/game")
    public GameDTO insertGame(@RequestBody Game g) {
        return new GameDTO(gameService.saveGame(g));
    }

    @DeleteMapping("game/{id}")
    public boolean deleteGame(@PathVariable("id") long id) {

        /*
         * verifier si un jeu existe pour l'identifiant
         * si le jeu existe:supprimer le game et renvoyer true
         * sinon renvoyer false
         * optinal=on peut retourner qq chose de null
         */
        Optional<Game> g = gameService.getGame(id);
        if (g.isPresent()) {
            gameService.deleteGame(id);
            return true;
        } else {
            return false;
        }

    }

    @PutMapping("/game/{id}")
    public GameDTO updateGame(@PathVariable("id") long id, @RequestBody Game game) {
        Optional<Game> g = gameService.getGame(id);
        if (g.isPresent()) {
            Game gameToUpdate = g.get();
            if (game.getTitle() != null) {
                gameToUpdate.setTitle(game.getTitle());
            }
            if (game.getMinPlayers() != null) {
                gameToUpdate.setMinPlayers(game.getMinPlayers());
            }
            if (game.getMaxPlayers() != null) {
                gameToUpdate.setMaxPlayers(game.getMaxPlayers());
            }
            return new GameDTO(gameService.saveGame(gameToUpdate));
        }
        return null;
    }

}