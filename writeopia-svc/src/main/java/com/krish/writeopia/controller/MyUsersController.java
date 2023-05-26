package com.krish.writeopia.controller;

import java.security.Principal;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.krish.writeopia.models.Connections;
import com.krish.writeopia.models.MyUsers;
import com.krish.writeopia.business_layer.MyUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * User Controller
 */
@RestController
public class MyUsersController {
    @Autowired
    private MyUsersService myUsersService;

    /**
     *
     * Private User Profile
     */
    @GetMapping("/user/profile")
    Optional<MyUsers> userProfile(Principal principal){
        return myUsersService.showUserByUserNameService(principal.getName());
    }

    /**
     * Public User Profile
     */
    @GetMapping("/user/{userName}")
    public Optional<MyUsers> publicProfile(@PathVariable String userName){
        return myUsersService.showUserByUserNameService(userName);
    }


    @GetMapping("/user/connections-all-type/{userName}")
    public List<Connections> getAllTypeConnectionsUSer(@PathVariable String userName){
        return myUsersService.alltypeConnectionsUserService(userName);
    }
    @GetMapping("/user/connections/{userName}")
    public List<Connections> userConnections(@PathVariable String userName){
        return myUsersService.showUserConnectionsListService(userName);
    }
    @GetMapping("/user/pending-connections/{userName}")
    public List<Connections> userConnectionsPending(@PathVariable String userName){
        return myUsersService.showUserPendingListService(userName);
    }
    @GetMapping("/user/followers/{userName}")
    public List<Connections> userFollowers(@PathVariable String userName){
        return myUsersService.showUserFollowersListService(userName);
    }
    @PostMapping("/user/connections/add")
    public void addModConnection(@RequestBody Connections connection){
        myUsersService.addModConnectionService(connection);
    }
    @GetMapping("user/connections/remove/{id}")
     public void removeConnection(@PathVariable Long id){
        myUsersService.removeConnectionService(id);
     }

    @PostMapping("/user/connections/status")
    public Connections connectionStatus(@RequestBody ObjectNode jsonNodes){
        String receiver = jsonNodes.get("receiver").asText();
        String sender = jsonNodes.get("sender").asText();
        return myUsersService.checkConnectionStatus(receiver, sender);
    }



    @GetMapping("/user")
    public String userHome(){
        return "Welcome User";
    }

    @PostMapping("/user/add/")
    public void addUser(@RequestBody MyUsers user){
        myUsersService.addUserService(user);
    }
    @GetMapping("/admin")
    public String adminHome(){
        return "Welcome Admin";
    }
}
