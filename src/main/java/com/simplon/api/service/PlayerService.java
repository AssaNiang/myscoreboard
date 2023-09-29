package com.simplon.api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplon.api.model.Player;
import com.simplon.api.repository.PlayerRepository;

import lombok.Data;

@Data
@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public Iterable<Player>getAllPlayers(){
        return playerRepository.findAll();
    }
    public Optional<Player>getPlayer(final long id){
        return playerRepository.findById(id);
    }
    public Player savePlayer(Player p){
        return this.playerRepository.save(p);

    }
    public void deletePlayer(final long id){
        this.playerRepository.deleteById(id);
    }

}