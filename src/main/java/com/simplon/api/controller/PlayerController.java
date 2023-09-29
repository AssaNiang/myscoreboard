package com.simplon.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.simplon.api.model.Contest;
import com.simplon.api.model.Game;
import com.simplon.api.model.Player;
import com.simplon.api.model.dto.ContestDTO;
import com.simplon.api.model.dto.PlayerDTO;
import com.simplon.api.service.PlayerService;

@RestController
public class PlayerController {
    @Autowired
    private PlayerService playerService;

    @GetMapping("/players")
    // public Iterable<Player> allPlayers() {
    //     return playerService.getAllPlayers();
    // }
     public Iterable<PlayerDTO> allPlayers() {
        List<PlayerDTO> playersDTO = new ArrayList<PlayerDTO>();
        Iterable<Player> players = playerService.getAllPlayers();
        for (Player player : players) {
            playersDTO.add(new PlayerDTO(player));
        }
        return playersDTO;
    }

    @GetMapping("/player/{id}")
    public PlayerDTO playerById(@PathVariable("id") long id) {
        Optional<Player> p = playerService.getPlayer(id);
        if (p.isPresent()) {
            return new PlayerDTO(p.get()); 
        } else {
            return null;
        }
    }

    @PostMapping("/player")
    public Player insertPlayer(@RequestBody Player p) {
        return playerService.savePlayer(p);
    }

    @DeleteMapping("/player/{id}")
    public boolean deletePlayer(@PathVariable("id") long id) {
        Optional<Player> p = playerService.getPlayer(id);
        if (p.isPresent()) {
            playerService.deletePlayer(id);
            return true;
        } else {
            return false;
        }
    }

    @PutMapping("player/{id}")
       public PlayerDTO updatePlayer(@PathVariable("id") long id, @RequestBody Player player) {
        Optional<Player> p = playerService.getPlayer(id);
        if (p.isPresent()) {
            Player playerToUpdate = p.get();
            if (player.getEmail() != null) {
                playerToUpdate.setEmail(player.getEmail());
            }
            if (player.getNickname() != null) {
                playerToUpdate.setNickname(player.getNickname());
            }
            
            return new PlayerDTO(playerService.savePlayer(playerToUpdate));
        }
        return null;
    }

     @GetMapping("/player/{id}/contests")
    public Iterable<ContestDTO> contests(@PathVariable("id") long id) {
        Optional<Player> p = playerService.getPlayer(id);
        if( p.isPresent() ) {
            List<Contest> contests = p.get().getContests();
            List<ContestDTO> contestsDTO = new ArrayList<ContestDTO>();
            for (Contest contest : contests) {
                contestsDTO.add(new ContestDTO(contest));
            }
            return contestsDTO;
        }
        return null;
    }

}