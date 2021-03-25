package com.example.BetApp.Models;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name="userinfo")
public class UserProfile {	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int userId;
    
    @Column(name="name")
    private String name;
    
    @Column(name="price")
    private int price;
    
    @Column(name="bet")
    private int bet;
    
    @Column(name="image")
    private String imageUrl;
    
    @Column(name="won")
    private int won;
    
    @Column(name="lost")
    private int lost;
    
    @Column(name="level")
    private int level;
}
