package com.simplon.api.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class Contest {
    @Id /* clé primaire */
    @GeneratedValue(strategy = GenerationType.IDENTITY) /* auto increment */
    private Long id;

    @Column(name = "start_date")
    private Date startDate;

    @ManyToOne // plusieurs contest(partie) pour un jeu la clé etrangere est dans cette table
    @JoinColumn(name = "id_game")
    private Game game;

    @ManyToOne
    @JoinColumn(name = "id_winner") // si je voulais winner_id il faut pas ajouter @joinColum
    private Player winner;

    @ManyToMany(mappedBy = "contests")//dans l'entité "contest" contests fait la relation 
    private List<Player> players;
}