package com.simplon.api.model.dto;

import java.sql.Date;

import com.simplon.api.model.Contest;

import lombok.Data;

@Data
public class ContestDTO {
    private Long id;
    private Date startDate;
    private String game;
    private String winner;

    public ContestDTO(Contest contest) {
        this.id = contest.getId();
        this.startDate = contest.getStartDate();
        this.game = contest.getGame().getId() + "-" + contest.getGame().getTitle();

        if (contest.getWinner() != null) {
            this.winner = contest.getWinner().getNickname();

        } else {
            this.winner = "pas de vainqueur";
        }
       // this.winner =contest.getWinner() !=null?contest.getWinner().getNickname():"pas de vaiqueur";
        /* EXERCICE : le ContestDTO doit contenir la liste des players participants.
         *                  C'st une liste de String qui ressemble à 
         *    id - nickmane (email)
         * 
         */
    }

}