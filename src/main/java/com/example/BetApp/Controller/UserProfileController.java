package com.example.BetApp.Controller;
//import com.google.appengine.repackaged.org.json.JSONArray; 
import org.json.*;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.BetApp.Models.UserData;
import com.example.BetApp.Models.UserProfile;
import com.example.BetApp.Repository.UserProfileRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api")
public class UserProfileController {

	@Autowired
	private UserProfileRepository userRepository;
	
    private final ObjectMapper mapper = new ObjectMapper();
    
    @GetMapping ("/search/{name}")
	public ResponseEntity<List<UserProfile>> searchByPlayers(@PathVariable String name) {
        List<UserProfile> users = userRepository.searchByPlayerName(name);
        return Objects.nonNull(users) ? ResponseEntity.ok(users) : ResponseEntity.notFound().build();
	}
    
    @GetMapping ("/getAllUsers")
	public ResponseEntity<List<UserProfile>> getAllUsers() {
        List<UserProfile> users = userRepository.findAll();
        return Objects.nonNull(users) ? ResponseEntity.ok(users) : ResponseEntity.notFound().build();
	}
    
    @RequestMapping(value="/saveAllUsers", method = RequestMethod.POST)
    public ResponseEntity<List<UserProfile>> saveAllUsers(@RequestBody List<UserData> userData) {
//    	JSONArray data = new JSONArray(userData);
//    	List<UserData> usdata = mapper.readValue(data, UserData.class);
//    	userRepository.deleteAll();
    	List<UserProfile> addedUsers = new ArrayList<>();
    	for(UserData data1: userData) {
    		UserProfile profile = new UserProfile();
    		profile.setName(data1.getName());
    		profile.setPrice(data1.getPrice());
    		profile.setBet(data1.getBet());
    		profile.setImageUrl(data1.getProfileImage());
    		profile.setLevel(0);
    		profile.setLost(0);
    		profile.setWon(0);
    		userRepository.save(profile);
    		addedUsers.add(profile);
    	}
    	return Objects.nonNull(addedUsers) ? ResponseEntity.ok(addedUsers) : ResponseEntity.notFound().build();
    }
    
    @RequestMapping(value="/updatePrice", method = RequestMethod.POST)
    public ResponseEntity<List<UserProfile>> updatePriceById(@RequestBody Map<String, List<String>> results) {
    	List<UserProfile> profile = new ArrayList<>();
    	List<String> playerIds = results.get("players");
    	List<String> wonIds = results.get("won");
    	for(String id: playerIds) {
    	UserProfile userData = new UserProfile();
    	userRepository.findById(Integer.parseInt(id)).ifPresent( player -> {
    		if(wonIds.contains(player.getUserId())) {
    			userData.setName(player.getName());
        		userData.setUserId(player.getUserId());
        		userData.setImageUrl(player.getImageUrl());
        		userData.setLevel(player.getLevel()+1);
        		userData.setBet(player.getBet());
        		userData.setLost(player.getLost());
        		userData.setWon(player.getWon()+1);
        		userData.setPrice(player.getPrice()*2);
            	userRepository.save(userData);
    		}
    		else {
    			userData.setName(player.getName());
        		userData.setUserId(player.getUserId());
        		userData.setImageUrl(player.getImageUrl());
        		userData.setLevel(player.getLevel()+1);
        		userData.setBet(player.getBet());
        		userData.setLost(player.getLost()+1);
        		userData.setWon(player.getWon());
        		userData.setPrice(player.getPrice());
            	userRepository.save(userData);
    		  }
          	profile.add(userData);
    		});
    	}
    	return Objects.nonNull(profile) ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
    }
    
}
