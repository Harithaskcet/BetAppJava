package com.example.BetApp.Models;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
public class UserData {
	    @Id
		@JsonProperty("Name")
		@JsonAlias("name")
		private String name;
		private int Price;
		private int Bet;
		
		@JsonProperty("Profile Image")
		@JsonAlias("ProfileImage")
		private String ProfileImage;
}
