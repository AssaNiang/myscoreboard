package com.simplon.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ManyToAny;

import lombok.Data;

@Data
@Entity

public class Player {
    @Id /*clé primaire*/
    @GeneratedValue(strategy = GenerationType.IDENTITY)/*auto increment  */
    private Long id;
    private String email;
    @Column(name="nickname")
    private String nickname;

    @OneToMany(mappedBy = "winner")
    private List<Contest>wins;
    @ManyToMany(cascade= CascadeType.ALL)
    @JoinTable(name="player_contest",joinColumns = { @JoinColumn(name ="id_player")},inverseJoinColumns ={ @JoinColumn(name="id_contest")})
    private List<Contest>contests =new ArrayList<Contest>();//pour mettre une valeure par défaut 

}