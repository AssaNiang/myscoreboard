package com.simplon.api.model.dto;

import java.util.ArrayList;
import java.util.List;

import com.simplon.api.model.Contest;
import com.simplon.api.model.Game;

import lombok.Data;

@Data
public class GameDTO {
    private Long id;
    private String title;
    private Integer minPlayers;
    private Integer maxPlayers;
    private List<String> contests;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.title = game.getTitle();
        this.minPlayers = game.getMinPlayers();
        this.maxPlayers = game.getMaxPlayers();
        List<String> strings = new ArrayList<String>();
        if (game.getContests() != null) {
            for (Contest contest : game.getContests()) {
                strings.add("Partie n°" + contest.getId() + "du" + contest.getStartDate());
            }

        }

        this.contests = strings;

    }
}