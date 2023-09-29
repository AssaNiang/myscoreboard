package com.simplon.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Data/*permet de ne pas ajouter les getters et les setter dans la classe */
@Entity/*cette classe à le role d'une entité */
public class Game{
    @Id /*clé primaire*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*auto increment  */
    private Long id;
    private String title;
    @Column(name="min_players")
    private Integer minPlayers;
    @Column(name="max_players")
    private Integer maxPlayers;
    @OneToMany(mappedBy = "game")//game = le game de la propriété game de contest
    private List<Contest>contests;
}